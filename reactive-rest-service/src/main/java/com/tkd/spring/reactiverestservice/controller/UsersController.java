package com.tkd.spring.reactiverestservice.controller;

import com.tkd.spring.reactiverestservice.dto.UserDto;
import com.tkd.spring.reactiverestservice.entity.User;
import com.tkd.spring.reactiverestservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserDto> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public Flux<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Mono<UserDto> getUserById(@PathVariable("userId") Integer userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public Mono<UserDto> updateUserById(@PathVariable("userId") Integer userId,
                                     @RequestBody UserDto user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
    }

}
