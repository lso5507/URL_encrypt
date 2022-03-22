package com.urlenc.service;

import com.urlenc.common.Utils;
import com.urlenc.domain.Member;
import com.urlenc.domain.MemberLogin;
import com.urlenc.domain.URI;
import com.urlenc.repository.URIRepository;
import com.urlenc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    /**
     * 회원가입, 해당 URL에 대한 유효성검사, 로그인
     */
    @Autowired
    UserRepository userRepository;

    public boolean save(Member member){
        try {
            userRepository.save(member);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Member login(MemberLogin member){
        try{
            Optional<Member> userData = userRepository.findByName(member.getUsername());
            if(userData.isEmpty())
                return null;
            Member memberData = userData.get();
            String encrypt = Utils.getEncrypt(member.getPassword(), memberData.getSalt());
            if(encrypt.equals(userData.get().getPassword())){
                return memberData;
            }else{
                //패스워드 다름
                return null;
            }


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
