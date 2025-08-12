package com.the.ultimate.springboot.course.by.alibou.repository;

import com.the.ultimate.springboot.course.by.alibou.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
