package com.wanichnun.lab.sparkspringboot.controller.fresh;

import com.wanichnun.lab.sparkspringboot.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping(path = "/hello")
    public String hello() {
        return helloService.getHello("SPRING");
    }

    @PostMapping(path = "/requestMapper")
    public Map body(@RequestBody Map<String, String> request) {
        return helloService.getRequest(request);
    }
}
