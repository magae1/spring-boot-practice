package org.conan.bootpractice.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "todo")
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tno;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 20, nullable = false)
    private String writer;

    @Column(nullable = false)
    private boolean complete;

    @Column(nullable = false)
    private LocalDate dueDate;
}
