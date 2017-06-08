package com.wanichnun.lab.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.valid4j.Validation.validate;

@Service
public class ExampleService {
    @HystrixCommand(fallbackMethod = "handleCallFailed")
    public HttpEntity<String> call(String url) throws Exception {
        validate(url != null, new Exception("Please specify url"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        return new RestTemplate().exchange(url, HttpMethod.GET, requestEntity, String.class);
    }

    public HttpEntity<String> handleCallFailed(String url) {

        return new ResponseEntity(new HashMap<>(), HttpStatus.OK);
    }
}
