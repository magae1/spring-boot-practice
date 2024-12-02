package org.conan.bootpractice.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import org.conan.bootpractice.domain.Memo;


@SpringBootTest
@Log4j2
public class MemoRepositoryTests {
    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        log.info(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..." + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        Long mno = 10L;
        Optional<Memo> res = memoRepository.findById(mno);
        log.info("==================");
        if (res.isPresent()) {
            Memo memo = res.get();
            log.info(memo);
        }
    }

    @Transactional
    @Test
    public void testSelect2() {
//        Long mno = 5L;
//        Memo memo = memoRepository.getOne(mno);
//        log.info("==================");
//        log.info(memo);
    }

    @Test
    public void testUpdate() {
//        Memo memo = Memo.builder().mno(5L).memoText("Sample updated").build();
//        log.info(memoRepository.save(memo));
    }

    @Test
    public void testDelete() {
        Long mno = 5L;
        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> res = memoRepository.findAll(pageable);
        log.info(res);
        log.info("==========");
        log.info("Total Pages: " + res.getTotalPages());
        log.info("Total counts: " + res.getTotalElements());
        log.info("Page number: " + res.getNumber());
        log.info("Page size: " + res.getSize());
        log.info("has next?: " + res.hasNext());
        log.info("is first page?: " + res.isFirst());
    }

    @Test
    public void testSort() {
        Sort sort = Sort.by(Sort.Direction.DESC, "mno");
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<Memo> res = memoRepository.findAll(pageable);
        res.get().forEach(System.out::println);
    }

    @Test
    public void testQueryMethod() {
        List<Memo> memoList = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
        memoList.forEach(log::info);
    }

    @Test
    public void testQueryMethodWithPageable() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));
        Page<Memo> res = memoRepository.findByMnoBetween(10L, 50L, pageable);
        res.get().forEach(log::info);
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethod() {
        memoRepository.deleteByMnoLessThan(20L);
    }

    @Test
    public void testQueryAnnotationMethod() {
        List<Memo> memoList = memoRepository.getListDesc();
        memoList.forEach(log::info);
    }
}
