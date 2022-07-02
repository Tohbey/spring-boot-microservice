package com.example.albumservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="albums")
@Data
public class Album implements Serializable {
    private static final long serialVersionUID = -2731425678149216053L;

    @Id
    @GeneratedValue
    private long id;
}
