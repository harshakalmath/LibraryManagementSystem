package com.application.library.controller;

import com.application.library.model.Account;
import com.application.library.model.Book;
import com.application.library.model.Member;
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

@RestController("/member")
public class MemberController {

    @Autowired
    private AccountRepository memberRepository;

    @Autowired
    private MongoTemplate mongoTemplateMember;


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
        mongoTemplateMember.save(member);
    }

}
