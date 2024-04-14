package com.laaz.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

//    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
//    private List<Role> roles;
//
//    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
//    private List<Section> sections;
}
