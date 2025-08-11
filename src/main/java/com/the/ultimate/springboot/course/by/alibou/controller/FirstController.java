package com.the.ultimate.springboot.course.by.alibou.controller;

import com.the.ultimate.springboot.course.by.alibou.entity.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from my first controller";
    }

    @PostMapping("/post")
    public String post(
          @RequestBody String message
    ) {
        return "Request Accepted and message is : = " + message;
    }

    @PostMapping("/post-order")
    public String post(
          @RequestBody Order order
    ) {
        return "Request Accepted and order is : = " + order;
    }

}
