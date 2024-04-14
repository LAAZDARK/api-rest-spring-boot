package com.laaz.demo.repositories;

import com.laaz.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    boolean existsByKey(String key);
}
