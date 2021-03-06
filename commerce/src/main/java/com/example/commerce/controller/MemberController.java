package com.example.commerce.controller;

import com.example.commerce.domain.Member;
import com.example.commerce.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class MemberController
{

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    @GetMapping("members/new")
    public String createAccount(){
        return "members/createMember";
    }


    @PostMapping("/members/new")
    public String create(MemberForm form) {

            Member member = new Member();
            member.setName(form.getName());
            member.setEmail(form.getEmail());
            member.setPassword(form.getPassword());
            memberService.join(member);

        return "members/createMember";

    }


    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members" , members);

        return "members/memberList";

    }


}
