package com.info.student.service.Impl;

import com.info.student.model.Student;
import com.info.student.repository.StudentRepository;
import com.info.student.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepo;


    public StudentServiceImpl(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }


    @Override
    @Transactional
    public Student create(String firstName, String lastName, String email, int age) {
        Student s = new Student();
        s.setFirstName(firstName);
        s.setLastName(lastName);
        s.setEmail(email);
        s.setAge(age);
        try {
            return studentRepo.save(s);
        } catch (DataIntegrityViolationException ex) {
        // likely unique constraint on email
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }
    }


    @Override
    public Student getById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }


    @Override
    public Optional<Student> getByEmail(String email) {
        return studentRepo.findByEmail(email);
    }


    @Override
    public List<Student> list() {
        return studentRepo.findAll();
    }


    @Override
    @Transactional
    public Student update(Long id, String firstName, String lastName, String email, int age) {
        Student s = getById(id);
        s.setFirstName(firstName);
        s.setLastName(lastName);
        s.setEmail(email);
        s.setAge(age);
        try {
            return studentRepo.save(s);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }
    }


    @Override
    @Transactional
    public void delete(Long id) {
        Student s = getById(id);
        studentRepo.delete(s);
    }
}
