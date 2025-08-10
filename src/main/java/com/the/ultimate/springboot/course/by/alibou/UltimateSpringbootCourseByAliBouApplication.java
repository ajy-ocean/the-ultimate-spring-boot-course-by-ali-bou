package com.the.ultimate.springboot.course.by.alibou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class UltimateSpringbootCourseByAliBouApplication {

	public static void main(String[] args) {

		var app = new SpringApplication(UltimateSpringbootCourseByAliBouApplication.class);
		app.setDefaultProperties(Collections.singletonMap("spring.profiles.active", "dev"));
		var context = app.run(args);

		MyFirstService myFirstService = context.getBean(MyFirstService.class);
		System.out.println(myFirstService.tellAStory());
		System.out.println(myFirstService.getCustomProperty());
		System.out.println(myFirstService.getNumberCustomProperty());
	}

}
