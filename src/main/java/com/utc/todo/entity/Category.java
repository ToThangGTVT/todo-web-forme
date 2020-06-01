package com.utc.todo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Date createDate;
    private Date updateDate;
    private String description;


    @OneToMany(mappedBy = "category")
    private List<Post> posts;
}
