package com.tkd.spring.reactiverestservice.repository;

import com.tkd.spring.reactiverestservice.entity.Department;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DepartmentRepository extends ReactiveCrudRepository<Department, Integer> {

    Mono<Department> findByUserId(Integer userId);
}
