package com.example.userservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="users")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -2731425678149216053L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, length=50)
    private String firstName;

    @Column(nullable=false, length=50)
    private String lastName;

    @Column(nullable=false, length=120, unique=true)
    private String email;

    @Column(nullable=false, unique=true)
    private String userId;

    @Column(nullable=false, unique=true)
    private String encryptedPassword;

    private Integer isActive;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
