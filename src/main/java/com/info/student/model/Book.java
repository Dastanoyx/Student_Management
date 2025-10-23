package com.info.student.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name="book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="book_id" , nullable = false)
    private Long id;

    @Column(name ="bookname" , nullable = false, columnDefinition = "TEXT")
    private String bookName;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime bookDate;


    @ManyToOne
    @JoinColumn(name="student_id", nullable = false)
    private Student student;
}
