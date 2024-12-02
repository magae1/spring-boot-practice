package org.conan.bootpractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.conan.bootpractice.domain.entity.Member;


public interface MemberRepository extends JpaRepository<Member, String> {
}
