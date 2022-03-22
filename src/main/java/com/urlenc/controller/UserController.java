package com.urlenc.controller;

import com.urlenc.domain.AuthInfo;
import com.urlenc.domain.Member;
import com.urlenc.domain.MemberLogin;
import com.urlenc.domain.URI;
import com.urlenc.service.UrlService;
import com.urlenc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
public class UserController {
    @Autowired
    private UserService userService;
    UrlService urlService;
    @GetMapping("/")
    public String main(){
        return "member/main";
    }
    @GetMapping("login")
    public String loginForm(){
        return "member/login";
    }
    @PostMapping("login")
    public String userLogin(@ModelAttribute("member") MemberLogin member, HttpSession httpSession,@RequestParam(value = "id", required = false)Long id){

        Member memberData = userService.login(member);
        if(memberData!=null){
             AuthInfo authInfo = new AuthInfo();
             authInfo.setId(memberData.getId());
             authInfo.setSalt(memberData.getSalt());
             httpSession.setAttribute("authInfo",authInfo);
            if(id!=null){
                return "redirect:/url/"+id;
            }
             return "member/main";
         }

         return "member/failed";

    }
    @GetMapping("/save")
    public String userSaveForm(){
        return "member/new";
    }
    @PostMapping("/save")
    public String userSave(@ModelAttribute("member") Member member){
        if(userService.save(member)){
            return "member/main";
        }else{
            return "member/failed";
        }
    }
}
