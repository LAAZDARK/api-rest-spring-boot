package com.laaz.demo.services.impl;

import com.laaz.demo.dtos.RoleDto;
import com.laaz.demo.entities.Role;
import com.laaz.demo.exceptions.NotFoundException;
import com.laaz.demo.mappers.RoleMapper;
import com.laaz.demo.repositories.RoleRepository;
import com.laaz.demo.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public Optional<Role> findById(UUID id) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isEmpty()) {
            throw new NotFoundException("Role not found", HttpStatus.NOT_FOUND.value());
        }

        return Optional.of(roleOptional.get());
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        try {
            Role role = RoleMapper.toRole(roleDto);
            String key = role.getName().toUpperCase().replace(" ", "_");

            if (roleRepository.existsByKey(key)) {
                key = key + "_" + UUID.randomUUID().toString().toUpperCase().substring(0, 5);
            }

            role.setKey(key);
            roleRepository.save(role);

            return RoleMapper.toRoleDto(role);
        } catch (Exception e) {
            throw e;
        }



    }

    @Override
    public Role update(UUID id, RoleDto roleDto) {
        try {
            Optional<Role> roleOptional = roleRepository.findById(id);

            if (roleOptional.isEmpty()) {
                throw new NotFoundException("Role not found", HttpStatus.NOT_FOUND.value());
            }

            Role role = roleOptional.get();
            role.setName(roleDto.getName());
            role.setUpdatedAt(new Date());

            return roleRepository.save(role);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteById(UUID id) {
        try {
            Optional<Role> roleOptional = roleRepository.findById(id);

            if (roleOptional.isEmpty()) {
                throw new NotFoundException("Role not found", HttpStatus.NOT_FOUND.value());
            }

            roleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
