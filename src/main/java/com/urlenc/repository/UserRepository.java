package com.urlenc.repository;

import com.urlenc.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final EntityManager em;

    //생성자 함수 1개일경우 어노테이션 생략가능
    public UserRepository(EntityManager em){

        this.em=em;
    }
    @Transactional
    public void save(Member member)throws Exception{
        em.persist(member);
    }
    @Transactional(readOnly = true)
    public void login(Member member)throws Exception{

    }
    @Transactional(readOnly = true)
    public Optional<Member>findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    @Transactional(readOnly = true)
    public Optional<Member> findByName(String userName) {
        List<Member> result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", userName)
                .getResultList();
        return result.stream().findAny();

    }
}
