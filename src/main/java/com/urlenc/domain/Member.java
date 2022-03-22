package com.urlenc.domain;

import com.urlenc.common.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "url_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    private String salt;
    public Member(){

    }
    public Member(String username,String password){
        this.username=username;
        this.password=password;// hash필요
        this.salt=Utils.getSalt();


    }


    public void setPassword(String password){

        this.salt= Utils.getSalt();
        this.password=Utils.getEncrypt(this.password,this.salt);
    }
}
