package com.the.ultimate.springboot.course.by.alibou.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent(){
        StudentDto studentDto = new StudentDto(
          "john",
          "cena",
          "cena@gmail.com",
          1
        );

        Student student = studentMapper.toStudent(studentDto);
        assertEquals(studentDto.firstName(), student.getFirstName());
        assertEquals(studentDto.lastName(), student.getLastName());
        assertEquals(studentDto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentDto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        Student student = new Student(
                "James",
                "Smith",
                "james@gmail.com",
                20);

        StudentResponseDto response = studentMapper.toStudentResponseDto(student);
        assertEquals(response.firstName(), student.getFirstName());
        assertEquals(response.lastName(), student.getLastName());
        assertEquals(response.email(), student.getEmail());
    }

    @Test
    public void should_throw_null_pointer_when_studentDto_is_null() {
       var exp = assertThrows(NullPointerException.class,
                () -> studentMapper.toStudent(null));
       assertEquals("The student Dto should not be null", exp.getMessage());
    }
}