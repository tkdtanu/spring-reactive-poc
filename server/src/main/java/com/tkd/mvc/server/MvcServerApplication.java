package com.tkd.mvc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MvcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcServerApplication.class, args);
    }

}

@RestController
@RequestMapping("/greeting")
class Controller {

    @GetMapping
    public String getGreeting() throws InterruptedException {
        Thread.sleep(10000);
        return "Hello User";
    }

}
