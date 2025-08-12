package com.the.ultimate.springboot.course.by.alibou.controller;

import com.the.ultimate.springboot.course.by.alibou.entity.School;
import com.the.ultimate.springboot.course.by.alibou.record.SchoolDto;
import com.the.ultimate.springboot.course.by.alibou.repository.SchoolRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolController {
    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/schools")
    public SchoolDto addSchool(
            @RequestBody SchoolDto schoolDto
    ) {
        var school = toSchool(schoolDto);
        schoolRepository.save(school);
        return schoolDto;
    }

    private School toSchool(SchoolDto schoolDto) {
        return new School(schoolDto.schoolName());
    }

    @GetMapping("/schools")
    public List<SchoolDto> findAllSchools() {
        return schoolRepository.findAll()
                .stream()
                .map(this::toSchoolDto)
                .collect(Collectors.toList());
    }

    private SchoolDto toSchoolDto(School school) {
        return new SchoolDto(school.getSchoolName());
    }
}
