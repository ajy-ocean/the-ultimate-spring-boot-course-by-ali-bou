package com.the.ultimate.springboot.course.by.alibou.repository;

import com.the.ultimate.springboot.course.by.alibou.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByFirstNameContaining(String firstName);
}
