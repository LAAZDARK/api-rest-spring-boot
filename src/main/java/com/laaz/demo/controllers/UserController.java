package com.laaz.demo.controllers;

import com.laaz.demo.dtos.UserDto;
import com.laaz.demo.services.UserService;
import com.laaz.demo.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Object> create(@RequestBody UserDto userDto) {
        UserDto userResult = userService.create(userDto);
        return new ApiResponse<Object>(userResult, "Success", HttpStatus.CREATED.value());
    }

    @GetMapping
    public ApiResponse<Object> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                       @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Object users = userService.findAll(pageable);

        return new ApiResponse<>(users, "Success", HttpStatus.OK.value());
    }


    @GetMapping("/{id}")
    public ApiResponse<Object> findById(@PathVariable UUID id) {
        Optional<UserDto> userOptional = userService.findById(id);
        return new ApiResponse<>(userOptional);
    }

    @PutMapping("/{id}")
    public ApiResponse<Object> update(@PathVariable UUID id, @RequestBody UserDto userDto) {
        UserDto userResult = userService.update(id, userDto);
        return new ApiResponse<>(userResult);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> deleteById(@PathVariable UUID id) {
        boolean result = userService.deleteById(id);
        return new ApiResponse<>(result);
    }

}
