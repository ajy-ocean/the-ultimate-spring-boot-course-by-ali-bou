package com.the.ultimate.springboot.course.by.alibou.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentRepositoryTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {
        StudentDto studentDto = new StudentDto(
                "John",
                "Cena",
                "cena@gmail.com",
                17
        );
        Student student = new Student(
                "John",
                "Cena",
                "cena@gmail.com",
                17
        );
        Student savedStudent = new Student(
                "John",
                "Cena",
                "cena@gmail.com",
                17
        );
        savedStudent.setId(1);

        when(studentMapper.toStudent(studentDto))
                .thenReturn(student);
        when(studentRepository.save(student))
                .thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Cena",
                        "cena@gmail.com"));

        StudentResponseDto responseDto = studentService.saveStudent(studentDto);
        assertEquals(responseDto.firstName(), responseDto.firstName());
        assertEquals(responseDto.lastName(), responseDto.lastName());
        assertEquals(responseDto.email(), responseDto.email());

        verify(studentMapper, times(1))
                .toStudent(studentDto);
        verify(studentRepository, times(1))
                .save(student);
        verify(studentMapper, times(1))
                .toStudentResponseDto(savedStudent);
    }

    @Test
    public void should_return_all_students() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Cena",
                "cena@gmail.com",
                20));

        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Cena",
                        "cena@gmail.com"));
        List<StudentResponseDto> responseDtos = studentService.findAllStudents();
        assertEquals(students.size(), responseDtos.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id() {
       Integer studentId = 1;
        Student student = new Student(
                "John",
                "Cena",
                "cena@gmail.com",
                20);

        when(studentRepository.findById(studentId))
                .thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Cena",
                        "cena@gmail.com"));
        StudentResponseDto responseDto = studentService.findStudentById(studentId);
        assertEquals(responseDto.firstName(), student.getFirstName());
        assertEquals(responseDto.lastName(), student.getLastName());
        assertEquals(responseDto.email(), student.getEmail());
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    public void should_find_student_by_name() {
        String studentName = "cena";
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Cena",
                "cena@gmail.com",
                20));

        when(studentRepository.findAllByFirstNameContaining(studentName))
                .thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Cena",
                        "cena@gmail.com"));
        var responseDto = studentService.findStudentsByName(studentName);
        assertEquals(students.size(), responseDto.size());
        verify(studentRepository, times(1))
                .findAllByFirstNameContaining(studentName);
    }

    @Test
    public void should_delete_student_by_id() {
        Integer studentId = 1;
        doNothing().when(studentRepository)
                .deleteById(studentId);
        studentService.deleteStudent(studentId);
        verify(studentRepository, times(1))
                .deleteById(studentId);
    }

}