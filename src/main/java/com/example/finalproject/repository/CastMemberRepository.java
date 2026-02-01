package com.example.finalproject.repository;

import com.example.finalproject.model.CastMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastMemberRepository extends JpaRepository<CastMember,Long> {
}
