package com.example.userservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Role implements Serializable {
    private static final long serialVersionUID = -2731425678149216053L;
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false, unique=true)
    private String roleId;

    private String role;
}
