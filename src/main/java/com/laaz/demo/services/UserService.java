package com.laaz.demo.services;

import com.laaz.demo.dtos.UserDto;
import com.laaz.demo.entities.User;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Page<User> findAll(Pageable pageable);

    Optional<UserDto> findById(UUID id);

    UserDto create(UserDto userDto);

    UserDto update(UUID id, UserDto userDto);

    boolean deleteById(UUID id);

}
