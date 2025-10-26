package com.info.student.service;

import com.info.student.model.Student;


import java.util.List;
import java.util.Optional;


public interface StudentService {
    Student create(String firstName, String lastName, String email, int age);
    Student getById(Long id);
    Optional<Student> getByEmail(String email);
    List<Student> list();
    Student update(Long id, String firstName, String lastName, String email, int age);
    void delete(Long id);
}
