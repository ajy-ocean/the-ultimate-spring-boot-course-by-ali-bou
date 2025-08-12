package com.the.ultimate.springboot.course.by.alibou.controller;

import com.the.ultimate.springboot.course.by.alibou.entity.School;
import com.the.ultimate.springboot.course.by.alibou.entity.Student;
import com.the.ultimate.springboot.course.by.alibou.record.StudentDto;
import com.the.ultimate.springboot.course.by.alibou.record.StudentResponseDto;
import com.the.ultimate.springboot.course.by.alibou.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{student-id}")
    public Student findStudentById(
            @PathVariable("student-id") Integer id
    ) {
        return studentRepository.findById(id).orElse(new Student());
    }

    @PostMapping("/students")
    public StudentResponseDto addStudent(
            @RequestBody StudentDto studentDto
    ) {
        var student = toStudent(studentDto);
       var savedStudent = studentRepository.save(student);
       return toStudentResponseDto(savedStudent);
    }

    private Student toStudent(StudentDto studentDto) {
        var student = new Student();
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setEmail(studentDto.email());

        var school = new School();
        school.setId(studentDto.schoolId());
        student.setSchool(school);
        return student;
    }

    private StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }

    @GetMapping("/students/search/{student-name}")
    public List<Student> findStudentByFirstName(
            @PathVariable("student-name") String name
    ) {
        return studentRepository.findAllByFirstNameContaining(name);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(
            @PathVariable("student-id") Integer id
    ) {
        studentRepository.deleteById(id);
    }

}
