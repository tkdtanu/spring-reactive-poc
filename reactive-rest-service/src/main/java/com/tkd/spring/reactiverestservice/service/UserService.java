package com.tkd.spring.reactiverestservice.service;

import com.tkd.spring.reactiverestservice.dto.UserDto;
import com.tkd.spring.reactiverestservice.entity.User;
import com.tkd.spring.reactiverestservice.exception.DataNotFound;
import com.tkd.spring.reactiverestservice.repository.DepartmentRepository;
import com.tkd.spring.reactiverestservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public Mono<UserDto> createUser(User user) {
        return userRepository.save(user).map(User::toUserDto);
    }

    public Flux<UserDto> getAllUsers() {
        return userRepository.findAll().map(User::toUserDto);
    }

    public Mono<UserDto> getUserById(Integer userId) {
        return userRepository.findById(userId).map(User::toUserDto);
    }

    public Mono<UserDto> updateUser(Integer userId, UserDto user) {
        return userRepository.findById(userId)
                .flatMap(dbUser -> {
                    dbUser.setAge(user.getAge());
                    dbUser.setName(user.getName());
                    dbUser.setSalary(user.getSalary());
                    return userRepository.save(dbUser);
                })
                .then(Mono.error(new DataNotFound("")));
    }

    public void deleteUser(Integer userId) {
        userRepository.findById(userId)
                .onErrorMap(error -> new DataNotFound("Invalid UserId Provided"))
                .flatMap(dbUser -> userRepository.delete(dbUser));

    }

}
