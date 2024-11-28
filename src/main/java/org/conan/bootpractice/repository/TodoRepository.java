package org.conan.bootpractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.conan.bootpractice.domain.Todo;


public interface TodoRepository extends JpaRepository<Todo, Long> {
}
