package com.wanichnun.lab.aop.service;

import com.wanichnun.lab.aop.annotation.LogActivity;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @LogActivity
    public String hello(String text) {
        return "Hello " + text;
    }
}
