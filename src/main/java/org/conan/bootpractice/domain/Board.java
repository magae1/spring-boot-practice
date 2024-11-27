package org.conan.bootpractice.domain;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class Board {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;
    private Integer hit;
}
