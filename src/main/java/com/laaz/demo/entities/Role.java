package com.laaz.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "key", nullable = false, unique = true)
    private String key;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<module_role> moduleRoles;

}
