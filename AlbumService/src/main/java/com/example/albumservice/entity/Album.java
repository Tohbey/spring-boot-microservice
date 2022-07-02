package com.example.albumservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="albums")
@Data
public class Album implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @JoinColumn(name="user_id")
    private String userId;
    private String name;
    private String description;
}
