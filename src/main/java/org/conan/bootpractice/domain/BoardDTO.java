package org.conan.bootpractice.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class BoardDTO {
    private Long bno;
    private String title;
    private String content;
    private String writerEmail, writerName;
    private LocalDateTime regDate, modDate;
    private Integer hit;
    private Integer replyCount;
}
