package com.laaz.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "module_role")
public class module_role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "module_id", nullable = false)
    private String moduleId;

    @Column(name = "role_id", nullable = false)
    private String roleId;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
