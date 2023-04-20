package com.application.library.controller;

import com.application.library.model.Member;
import com.application.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/member")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MongoTemplate mongoTemplateMember;


    @DeleteMapping("/removeMember") // remove membership calls this api
    public String deleteMember(@RequestBody Member member) {
        memberRepository.delete(member);
        return "Removed Successfully";
    }

    @GetMapping("/getMembers")
    public List<Member> getMembers(){
        Query query = new Query();
        query.addCriteria(Criteria.where("_class").is("com.application.library.model.Member"));
        return mongoTemplateMember.find(query,Member.class);
    }

}
