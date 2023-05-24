package com.chb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chb.entity.MemberEntity;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

}