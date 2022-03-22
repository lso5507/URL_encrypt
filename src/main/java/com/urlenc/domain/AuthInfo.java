package com.urlenc.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthInfo {
    private long id;
    private String salt;

}
