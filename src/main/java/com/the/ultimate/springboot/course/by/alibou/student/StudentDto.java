package com.the.ultimate.springboot.course.by.alibou.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "first name should not be empty")
        String firstName,
        @NotEmpty(message = "last name should not be empty")
        String lastName,
        String email,
        Integer schoolId) {
}
