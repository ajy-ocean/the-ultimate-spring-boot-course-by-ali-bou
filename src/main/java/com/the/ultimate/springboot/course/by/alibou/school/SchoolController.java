package com.the.ultimate.springboot.course.by.alibou.school;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {
    private SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public SchoolDto addSchool(
            @RequestBody SchoolDto schoolDto
    ) {
        return schoolService.addSchool(schoolDto);
    }

    @GetMapping("/schools")
    public List<SchoolDto> findAllSchools() {
        return schoolService.findAllSchools();
    }

}
