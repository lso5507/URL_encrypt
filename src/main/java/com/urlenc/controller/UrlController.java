package com.urlenc.controller;

import com.urlenc.domain.AuthInfo;
import com.urlenc.domain.MemberLogin;
import com.urlenc.domain.URI;
import com.urlenc.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/url/*")
public class UrlController {
    @GetMapping("main")
    public String main(){

        return "url/main";
    }
    @Autowired
    UrlService urlService;
    @GetMapping("save")
    public String urlSaveForm(){

        return "url/new";
    }
    /*
    @GetMapping("/{id}")
    public String urlCheck(@PathVariable Long id, HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");

        if(authInfo==null){
            request.setAttribute("id",id);
            return "member/login";
        }
        String url = urlService.check(id, authInfo.getSalt());
        if(url==null){
            return "url/failed";
        }
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
            return "url/failed";
        }
        return "url/failed";

    }
    */
    @GetMapping("/{id}")
    public String urlCheckForm(@PathVariable Long id,HttpServletRequest request) {
        request.setAttribute("id",id);
        return "url/check";
    }
    @ResponseBody
    @GetMapping("/chan")
    public String urlCheckForm() {

        return "hello";
    }
    @PostMapping("/{id}")
    public String urlCheck(@RequestParam("id") Long id,@RequestParam("password") String password ,HttpServletResponse response){


        String url = urlService.check(id,password);
        if(url==null){
            return "url/failed";
        }
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
            return "url/failed";
        }
        return "url/failed";

    }
    @PostMapping("save")
    public String urlSave(HttpServletRequest request, @ModelAttribute("url") URI url){
//        url.setSalt(authInfo.getSalt());
        // Table 변경 후 수정 필요
        url.setUserid(1L);

        if(urlService.save(url)){
            String requestURI = request.getRequestURL().toString();
            String save = requestURI.split("save")[0];
            System.out.println("save = " + save);
            request.setAttribute("path",requestURI.split("save")[0]);
            request.setAttribute("url",url.getId());
            return "url/view";
        }
        return "url/failed";
    }
}
