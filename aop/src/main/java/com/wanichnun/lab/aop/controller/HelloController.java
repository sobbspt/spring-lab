package com.wanichnun.lab.aop.controller;

import com.wanichnun.lab.aop.annotation.LogExecutionTime;
import com.wanichnun.lab.aop.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping(path = "/hello")
    @LogExecutionTime
    public String getHello() {
        return helloService.hello("world!");
    }
}
