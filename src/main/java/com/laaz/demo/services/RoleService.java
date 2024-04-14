package com.laaz.demo.services;

import com.laaz.demo.dtos.RoleDto;
import com.laaz.demo.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface RoleService {
    Page<Role> findAll(Pageable pageable);

    Optional<Role> findById(UUID id);

    RoleDto create(RoleDto roleDto);

    Role update(UUID id, RoleDto roleDto);

    boolean deleteById(UUID id);

}
