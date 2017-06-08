package com.wanichnun.lab.hystrix.controller;

import com.wanichnun.lab.hystrix.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @Autowired
    private ExampleService exampleService;

    @GetMapping(path = "call")
    public HttpEntity<String> getHello(
            @RequestParam(name = "url", required = false) String url
    ) throws Exception {
        return exampleService.call(url);
    }
}
