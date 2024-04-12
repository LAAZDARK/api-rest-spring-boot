package com.laaz.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.Date;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class User {
    public static String STATUS_ACTIVE = "ACTIVE";
    public static String STATUS_INACTIVE = "INACTIVE";
    public static String STATUS_SUSPENDED = "SUSPENDED";
    public static String STATUS_PENDING = "PENDING";
    public static String STATUS_VERIFIED = "VERIFIED";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    @Size(min = 3, max = 100)
    private String name;

    @Column(name = "first_name", nullable = false)
    @NotEmpty
    @Size(min = 3, max = 100)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @ColumnTransformer(write = "LOWER(?)", read = "LOWER(email)")
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", columnDefinition = "varchar(50) default 'PENDING'")
    private String status;

    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP DEFAULT NULL")
    @JsonIgnore
    private Date deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
}
