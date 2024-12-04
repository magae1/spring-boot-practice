package org.conan.bootpractice.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Builder
@AllArgsConstructor
@Data
public class BoardPageRequestDTO {
    private int page;
    private int size;

    public BoardPageRequestDTO() {
        page = 1;
        size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}
