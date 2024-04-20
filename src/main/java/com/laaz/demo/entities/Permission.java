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
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "key", nullable = false, unique = true)
    private String key;

    @OneToMany(mappedBy = "permission", fetch = FetchType.LAZY)
    private List<ModulePermissionRole> modulePermissionRoles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private Module module;

}
