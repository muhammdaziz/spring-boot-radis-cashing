package com.example.springcaching.service;

import com.example.springcaching.entity.Student;
import com.example.springcaching.exceptions.RestException;
import com.example.springcaching.payload.ApiResult;
import com.example.springcaching.payload.StudentDTO;
import com.example.springcaching.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.management.timer.Timer;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public ApiResult<Boolean> save(StudentDTO studentDTO) {
        if (studentRepository.existsByEmail(studentDTO.getEmail()))
            throw RestException.restThrow("email exist", HttpStatus.CONFLICT);

        Student student = new Student();
        student.setFirstname(studentDTO.getFirstname());
        student.setLastname(studentDTO.getLastname());
        student.setEmail(studentDTO.getEmail());

        studentRepository.save(student);

        return ApiResult.successResponse();
    }
    @Override
    @Cacheable(cacheNames = {"students"}, key = "#id")
    public ApiResult<Student> get(Integer id) {

        slowCall();

        Optional<Student> optionalStudent = studentRepository.findById(id);

        Student student = optionalStudent.orElseThrow(
                () -> RestException.restThrow("Student not found", HttpStatus.NOT_FOUND));

        return ApiResult.successResponse(student);
    }

    @Override
    @Cacheable(cacheNames = {"student-list"})
    public ApiResult<List<Student>> getAll() {

        slowCall();

        List<Student> students = studentRepository.findAll();

        System.out.println(students);

        return ApiResult.successResponse(students);
    }

    @Override
//    @CachePut(cacheNames = {"student-cache"}, key = "#student.id")
    public ApiResult<Student> update(Student student) {

        studentRepository.save(student);

        return ApiResult.successResponse(student);
    }

    @Override
//    @CacheEvict(cacheNames = {"student-cache"}, key = "#id")
    public ApiResult<?> delete(Integer id) {

        if (!studentRepository.existsById(id))
            throw RestException.restThrow("no student found", HttpStatus.NOT_FOUND);

        studentRepository.deleteById(id);

        return ApiResult.successResponse();
    }


    private void slowCall(){
        try {
            System.out.println("------------------------------- Fetching data from database -----------------------------");

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


//    @CacheEvict(allEntries = true, cacheNames = { "student-list", "student-cache" })
//    @Scheduled(fixedDelay = Timer.ONE_SECOND * 10)
//    public void cacheEvict() {
//    }

}
