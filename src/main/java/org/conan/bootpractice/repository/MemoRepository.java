package org.conan.bootpractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.conan.bootpractice.domain.Memo;


public interface MemoRepository extends JpaRepository<Memo, Long> {
}
