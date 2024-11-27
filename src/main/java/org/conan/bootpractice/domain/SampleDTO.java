package org.conan.bootpractice.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;


@Data
@Builder(toBuilder = true)
public class SampleDTO {
    private Long sno;
    private String first;
    private String last;
    private LocalDateTime regTime;
}
