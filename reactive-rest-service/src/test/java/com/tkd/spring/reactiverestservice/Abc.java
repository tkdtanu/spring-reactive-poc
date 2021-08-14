package com.tkd.spring.reactiverestservice;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;

public class Abc {
    @Test
    public void testFluxCreation() {

        Flux<String> flux = Flux.just("Jeff", "Michel", "Alex", "Ben")
                .map(String::toUpperCase)
                .map(s -> {
                    System.out.println(Thread.currentThread().getName());
                    return s;
                });

        StepVerifier.create(flux)
                .expectNext("Jeff")
                .expectNext("Michel")
                .expectNext("Alex")
                .expectNext("Ben")
                .verifyComplete();
    }

    @Test
    public void testFluxCreationFromArray() {
        String[] names = new String[]{"Jeff", "Michel", "Alex", "Ben"};
        Flux<String> flux = Flux.fromArray(names);
        verifyFluxCreation(flux);
    }

    @Test
    public void testFluxRange() {
        StepVerifier.create(Flux.range(5, 2))
                .expectNext(5)
                .expectNext(6)
                .verifyComplete();
    }

    @Test
    public void testFluxInterval() {
        Flux flux = Flux.interval(Duration.ofSeconds(1))
                .take(5)
                .map(s -> {
                    System.out.println(s);
                    return s;
                });
        StepVerifier.create(flux)
                .expectNext(0L)
                .expectNext(1L)
                .expectNext(2L)
                .expectNext(3L)
                .expectNext(4L)
                .verifyComplete();
    }

    @Test
    public void testFluxCreationFromIterable() {
        List<String> names = List.of("Jeff", "Michel", "Alex", "Ben");
        Flux<String> flux = Flux.fromIterable(names);
        verifyFluxCreation(flux);
    }

    private void verifyFluxCreation(Flux<String> flux) {
        StepVerifier.create(flux)
                .expectNext("JEFF")
                .expectNext("MICHEL")
                .expectNext("ALEX")
                .expectNext("BEN")
                .verifyComplete();
    }
}
