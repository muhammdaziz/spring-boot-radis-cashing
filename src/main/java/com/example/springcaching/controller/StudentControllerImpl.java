package com.example.springcaching.controller;

import com.example.springcaching.entity.Student;
import com.example.springcaching.payload.ApiResult;
import com.example.springcaching.payload.StudentDTO;
import com.example.springcaching.service.StudentService;
import com.example.springcaching.service.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentControllerImpl implements StudentController{

    private final StudentServiceImpl studentService;

    @Override
    public ApiResult<Boolean> add(StudentDTO studentDTO) {

        return studentService.save(studentDTO);
    }

    @Override
    public ApiResult<Student> get(Integer id) {
        return studentService.get(id);
    }

    @Override
    public ApiResult<List<Student>> getAll() {
        return studentService.getAll();
    }

    @Override
    public ApiResult<Student> edit(Student student){
        return studentService.update(student);
    }

    @Override
    public ApiResult<?> remove(Integer id) {
        return studentService.delete(id);
    }
}
