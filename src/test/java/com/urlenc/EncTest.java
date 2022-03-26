package com.urlenc;

import com.urlenc.common.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;


public class EncTest {

    @Test
    public void hashTest(){

        String salt = Utils.getSalt();
        String encrypt = Utils.getEncrypt("1234", salt);
        Assertions.assertThat(Utils.getEncrypt("1234",salt)).isEqualTo(encrypt);
        Assertions.assertThat(Utils.getEncrypt("12345",salt)).isNotEqualTo(encrypt);

    }
    @Test
    public void AESTest() throws Exception {

        String salt = Utils.getSalt();
        String encrypt = Utils.getEncrypt("1234", salt);
        System.out.println("encrypt = " + encrypt);

        String enc = Utils.aes_encrypt("2134");
        String dec = Utils.aes_decrypt(enc);
        Assertions.assertThat(dec).isEqualTo("2134");


    }

}
