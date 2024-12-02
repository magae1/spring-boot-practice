package org.conan.bootpractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.conan.bootpractice.domain.entity.Reply;


public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
