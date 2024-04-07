package com.laaz.demo.services.impl;

import com.laaz.demo.dtos.UserDto;
import com.laaz.demo.entities.User;
import com.laaz.demo.exceptions.NotFoundException;
import com.laaz.demo.mappers.UserMapper;
import com.laaz.demo.repositories.UserRepository;
import com.laaz.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<UserDto> findById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found", HttpStatus.NOT_FOUND.value());
        }

        return Optional.of(UserMapper.mapToUserDto(userOptional.get()));
    }

    @Override
    public UserDto create(UserDto userDto) {
        try {
            User user = UserMapper.mapToUser(userDto);
            User userResult = userRepository.save(user);

            return UserMapper.mapToUserDto(userResult);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public UserDto update(UUID id, UserDto userDto) {
        try {
            Optional <User> userOptional = userRepository.findById(id);

            if (userOptional.isEmpty()) {
                throw new NotFoundException("User not found", HttpStatus.NOT_FOUND.value());
            }

            User user = userOptional.get();
            user.setName(userDto.getName());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setRole(userDto.getRole());
            user.setStatus(userDto.getStatus());
            user.setUpdatedAt(new Date());

            User userResult = userRepository.save(user);

            return UserMapper.mapToUserDto(userResult);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteById(UUID id) {
        try {
            userRepository.deleteById(id);

            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
