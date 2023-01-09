package com.example.springcaching.repository;

import com.example.springcaching.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    boolean existsByEmail(String email);

}
