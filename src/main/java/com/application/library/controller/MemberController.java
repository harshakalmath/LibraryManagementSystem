package com.application.library.controller;

import com.application.library.model.Member;
import com.application.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/member")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/addMember")  // register member calls this api
    public String saveMember(@RequestBody Member member){
        memberRepository.save(member);

        return "Added Successfully";
    }

    @DeleteMapping("/removeMember") // remove membership calls this api
    public String deleteMember(@RequestBody Member member) {
        memberRepository.delete(member);
        return "Removed Successfully";
    }

    @GetMapping("/getMembers")
    public List<Member> getMembers(){
        return memberRepository.findAll();
    }

}
