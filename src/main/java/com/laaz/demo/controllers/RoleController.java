package com.laaz.demo.controllers;

import com.laaz.demo.dtos.RoleDto;
import com.laaz.demo.entities.Role;
import com.laaz.demo.services.RoleService;
import com.laaz.demo.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Object> create(@Valid @RequestBody RoleDto roleDto) {
        RoleDto roleResult = roleService.create(roleDto);
        return new ApiResponse<Object>(roleResult, "Success", HttpStatus.CREATED.value());
    }

    @GetMapping
    public ApiResponse<Object> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                       @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Object roles = roleService.findAll(pageable);

        return new ApiResponse<>(roles, "Success", HttpStatus.OK.value());
    }

    @GetMapping("/{id}")
    public ApiResponse<Object> findById(@PathVariable UUID id) {
        Optional<Role> role = roleService.findById(id);
        return new ApiResponse<>(role);
    }

    @PutMapping("/{id}")
    public ApiResponse<Object> update(@PathVariable UUID id, @Valid @RequestBody RoleDto roleDto) {
        Role roleResult = roleService.update(id, roleDto);
        return new ApiResponse<>(roleResult);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> deleteById(@PathVariable UUID id) {
        boolean result = roleService.deleteById(id);
        return new ApiResponse<>(result);
    }

}
