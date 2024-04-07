package com.laaz.demo.dtos;

import com.laaz.demo.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String status;
    private Date createdAt;
    private Date updatedAt;

}
