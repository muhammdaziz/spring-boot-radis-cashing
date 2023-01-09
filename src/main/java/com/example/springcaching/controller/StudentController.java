package com.example.springcaching.controller;

import com.example.springcaching.entity.Student;
import com.example.springcaching.payload.ApiResult;
import com.example.springcaching.payload.StudentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/student")
public interface StudentController {

    @PostMapping("/save")
    ApiResult<Boolean> add(@RequestBody StudentDTO studentDTO);

    @GetMapping("/{id}")
    ApiResult<Student> get(@PathVariable Integer id);

    @GetMapping("/list")
    ApiResult<List<Student>> getAll();

    @PutMapping("/edit")
    ApiResult<Student> edit(@RequestBody Student student);

    @DeleteMapping("/{id}")
    ApiResult<?> remove(@PathVariable Integer id);
}
