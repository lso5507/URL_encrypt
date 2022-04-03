package com.urlenc.domain;


import com.urlenc.common.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "enc_url")
public class URI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    //EncURL
    private String url;
    private String password;
    private String salt;
    private Long userid;
    public void setPassword(String password) throws Exception {
        this.salt= Utils.getSalt();
        this.password=Utils.getEncrypt(password,this.salt);


    }


}
