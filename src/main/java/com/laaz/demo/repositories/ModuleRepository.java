package com.laaz.demo.repositories;

import com.laaz.demo.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {
    Module findByKey(String key);
}
