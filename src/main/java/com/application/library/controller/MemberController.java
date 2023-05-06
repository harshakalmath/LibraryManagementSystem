package com.application.library.controller;

import com.application.library.ObserverHelper;
import com.application.library.model.*;
import com.application.library.observer.ObserverData;
import com.application.library.repository.AccountRepository;
import com.application.library.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private AccountRepository memberRepository;

    @Autowired
    private MongoTemplate mongoTemplateMember;

    ObserverHelper helper = ObserverHelper.getInstance();


    @DeleteMapping("/removeMember") // remove membership calls this api
    public String deleteMember(@RequestBody Member member) {
        memberRepository.delete(member);
        return "Removed Successfully";
    }


    public Member getMember(String id){
        Optional<Account> member = memberRepository.findById(id);
        return (Member) member.get();

    }


    @GetMapping("/getMembers")
    public List<Member> getMembers(){
        Query query = new Query();
        query.addCriteria(Criteria.where("_class").is("com.application.library.model.Member"));
        return mongoTemplateMember.find(query,Member.class);
    }

    @PutMapping("/addBooksToMember")
    public void addBooksIssued(@RequestParam String id, @RequestBody String data1) throws JsonProcessingException {
        JSONObject data = new JSONObject(data1);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(data.getJSONArray("bookIds").toString());
        List<String> bookIds = objectMapper.readValue(data.getJSONArray("bookIds").toString(), List.class);
        Member member = getMember(id);
        List<String> bookIdsPrev = member.getBookIds();
        bookIdsPrev.addAll(bookIds);
        ObserverData observerData = helper.createObserverData(bookIds.size());
        helper.notifyObservers(observerData);
        mongoTemplateMember.save(member);
    }

    @GetMapping("/getFine")
    public double getFine(@RequestParam String data) {
        BookLending bookLending;
        if(data.equalsIgnoreCase(LibraryHelper.monthly))
            bookLending = new MonthlyLending();
        else if (data.equalsIgnoreCase(LibraryHelper.biWeekly))
            bookLending = new BiWeeklyLending();
        else bookLending = new WeeklyLending();
        return bookLending.lending();
    }

    @PostMapping("/removeBooksFromMember")
    public void removeBooksReturned(@RequestParam String id, @RequestBody String data1) throws JsonProcessingException {
        System.out.println("inside remove books api");
        JSONObject data = new JSONObject(data1);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(data.getJSONArray("bookIds").toString());
        List<String> bookIds = objectMapper.readValue(data.getJSONArray("bookIds").toString(), List.class);
        Member member = getMember(id);
        List<String> bookIdsPrev = member.getBookIds();
        bookIdsPrev.removeAll(bookIds);
        ObserverData observerData = helper.createObserverData(bookIds.size());
        helper.notifyObservers(observerData);
        mongoTemplateMember.save(member);

    }

}
