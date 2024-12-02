package org.conan.bootpractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.conan.bootpractice.domain.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {
}
