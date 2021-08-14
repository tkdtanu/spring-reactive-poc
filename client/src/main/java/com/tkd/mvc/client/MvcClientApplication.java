package com.tkd.mvc.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
public class MvcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcClientApplication.class, args);
    }

}

@FeignClient(name = "ServerClient", url = "${application.server.base-url}", decode404 = true)
interface ServerClient {

    @GetMapping("/greeting")
    String getGreeting();
}

@RestController
@RequestMapping("/greeting")
class MvcClientController {
    @Autowired
    private ServerClient serverClient;

    @GetMapping
    public String getGreeting() throws InterruptedException {
        return serverClient.getGreeting();
    }

}
