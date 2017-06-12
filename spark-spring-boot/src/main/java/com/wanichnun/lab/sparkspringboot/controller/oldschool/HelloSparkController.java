package com.wanichnun.lab.sparkspringboot.controller.oldschool;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.wanichnun.lab.sparkspringboot.config.Spark;
import com.wanichnun.lab.sparkspringboot.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

@Component
public class HelloSparkController implements Spark {

    @Autowired
    HelloService helloService;

    @Override
    public void register() {
        get("/hello", (request, response) -> helloService.getHello("SPARK"));
        post("/requestMapper", (request, response) -> {
            try {
                final Map<String, String> customerEntity = new ObjectMapper()
                        .readValue(request.body(), new TypeReference<Map<String, String>>() {
                        });

                return new Gson().toJson(helloService.getRequest(customerEntity));
            }catch (Exception e){
                return e.getMessage();
            }
        });
    }
}
