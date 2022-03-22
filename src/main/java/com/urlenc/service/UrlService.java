package com.urlenc.service;

import com.urlenc.common.Utils;
import com.urlenc.domain.Member;
import com.urlenc.domain.URI;
import com.urlenc.repository.URIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {
    @Autowired
    private URIRepository uriRepository;
    public boolean save(URI url){
        try {

            url.setUrl(url.getUrl(), url.getSalt());
            uriRepository.save(url);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public String check(Long id,String salt){
        Optional<URI> URIData = uriRepository.findById(id);
        if(URIData.isEmpty()){
            return null;
        }
        URI uri = URIData.get();
        try {
            String urlData = Utils.aes_decrypt(uri.getUrl(), salt);
            if(urlData.startsWith("http")){
                return urlData;
            }
            else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }

    }
}
