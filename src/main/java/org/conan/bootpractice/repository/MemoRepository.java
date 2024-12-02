package org.conan.bootpractice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.conan.bootpractice.domain.Memo;


@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long min, Long max);

    Page<Memo> findByMnoBetween(Long min, Long max, Pageable pageable);

    void deleteByMnoLessThan(Long num);

    @Query("SELECT m FROM Memo m ORDER BY m.mno DESC")
    List<Memo> getListDesc();
}
