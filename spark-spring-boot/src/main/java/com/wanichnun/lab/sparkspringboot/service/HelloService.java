package com.wanichnun.lab.sparkspringboot.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HelloService {
    public String getHello(String message) {
        return "Hello world " + message;
    }

    public Map getRequest(Map<String, String> customerEntity) {
        return customerEntity;
    }
}
