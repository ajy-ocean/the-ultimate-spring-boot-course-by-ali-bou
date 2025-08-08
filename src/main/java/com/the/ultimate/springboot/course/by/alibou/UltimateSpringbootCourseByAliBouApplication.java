package com.the.ultimate.springboot.course.by.alibou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UltimateSpringbootCourseByAliBouApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(UltimateSpringbootCourseByAliBouApplication.class, args);
		MyFirstService myFirstService = context.getBean(MyFirstService.class);
		System.out.println(myFirstService.tellAStory());
		System.out.println(myFirstService.getCustomPropertyFromAnotherFile());
	}

}
