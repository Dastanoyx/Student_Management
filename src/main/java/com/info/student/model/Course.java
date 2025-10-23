package com.info.student.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "course")
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "department", nullable = false)
    private String department;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrolment> enrolments;
}
