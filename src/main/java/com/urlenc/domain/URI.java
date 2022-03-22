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
    @Transient
    private String salt;
    private Long userid;
    public void setUrl(String url,String enc) throws Exception {
        this.url=Utils.aes_encrypt(url,enc);

    }


}
