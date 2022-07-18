package com.example.Dokkaebi.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //getSingleList()는 정확히 값이 하나가 아니면 오류로 된다.
    public Optional<Member> findByIdentity(String identity) {
        return em.createQuery("select m from Member m where m.identity = :identity", Member.class)
                .setParameter("identity", identity)
                .getResultList().stream().findFirst();
    }
}
