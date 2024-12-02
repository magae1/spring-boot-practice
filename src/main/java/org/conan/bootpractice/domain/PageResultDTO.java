package org.conan.bootpractice.domain;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Data
public class PageResultDTO<DTO, EN> {
    private int totalPage;
    private int page;
    private int size;
    private int start, end;
    private boolean prev, next;
    private List<Integer> pageNums;
    private List<DTO> dtoList;

    public PageResultDTO(Page<EN> page, Function<EN, DTO> mapper) {
        dtoList = page.stream().map(mapper).collect(Collectors.toList());
        totalPage = page.getTotalPages();
        makePageList(page.getPageable());
    }

    private void makePageList(Pageable pageable) {
        page = pageable.getPageNumber() + 1;
        size = pageable.getPageSize();
        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;
        start = tempEnd - 9;
        prev = start > 1;
        end = Math.min(totalPage, tempEnd);
        next = totalPage > tempEnd;
        pageNums = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
