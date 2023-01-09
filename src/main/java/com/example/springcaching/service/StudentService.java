package com.example.springcaching.service;

import com.example.springcaching.entity.Student;
import com.example.springcaching.payload.ApiResult;
import com.example.springcaching.payload.StudentDTO;

import java.util.List;

public interface StudentService {

    ApiResult<Boolean> save(StudentDTO studentDTO);
    ApiResult<?> delete(Integer id);

    ApiResult<Student> get(Integer id);

    ApiResult<List<Student>> getAll();

    ApiResult<Student> update(Student student);
}
