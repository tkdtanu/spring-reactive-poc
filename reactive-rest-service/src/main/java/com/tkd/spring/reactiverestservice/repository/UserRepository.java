package com.tkd.spring.reactiverestservice.repository;

import com.tkd.spring.reactiverestservice.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

    Flux<User> findByAgeGreaterThan(int age);
}
