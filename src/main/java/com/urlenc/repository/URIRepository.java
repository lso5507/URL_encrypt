package com.urlenc.repository;

import com.urlenc.common.Utils;
import com.urlenc.domain.Member;
import com.urlenc.domain.URI;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class URIRepository {
    private final EntityManager em;
    private UserRepository userRepository;
    //생성자 함수 1개일경우 어노테이션 생략가능
    public URIRepository(EntityManager em){

        this.em=em;
    }
    @Transactional
    public void save(URI uri){
        em.persist(uri);
    }

    @Transactional(readOnly = true)
    public Optional<URI>findById(Long id){
        URI uri = em.find(URI.class, id);
        return Optional.ofNullable(uri);
    }
}
