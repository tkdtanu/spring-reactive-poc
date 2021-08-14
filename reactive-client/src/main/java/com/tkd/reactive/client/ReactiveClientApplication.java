package com.tkd.reactive.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
//@EnableReactiveFeignClients
@Configuration
public class ReactiveClientApplication {

    @Bean(name = "serverClient")
    public WebClient serverClient(@Value("${application.server.base-url}") String serverBaseUrl) {
        return WebClient.create(serverBaseUrl);
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveClientApplication.class, args);
    }

}

//@Component
//@ReactiveFeignClient(name = "ServerClient", url = "${application.server.base-url}", decode404 = true)
interface ServerClient {

    @GetMapping("/greeting")
    Mono<String> getGreeting();
}


@RestController
@RequestMapping("/greeting")
class ReactiveClientController {
    @Autowired
    private WebClient serverClient;

    @GetMapping
    public Mono<String> getGreeting() throws InterruptedException {
        return serverClient.get().uri("/greeting").retrieve().bodyToMono(String.class);
    }

}

