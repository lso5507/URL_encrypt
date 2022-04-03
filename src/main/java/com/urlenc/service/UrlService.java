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
            uriRepository.save(url);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public String check(Long id,String salt){



        Optional<URI> URIData = uriRepository.findById(id);
        if(!URIData.isPresent()){
            return null;
        }
        URI uri = URIData.get();
        return uri.getUrl();

    }
}
