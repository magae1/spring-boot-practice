package org.conan.bootpractice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;
}
