package com.laaz.demo.services;

import com.laaz.demo.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RoleService {
    Page<Role> findAll(Pageable pageable);

    Role findById(UUID id);

    Role create(Role role);

}
