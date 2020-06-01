package com.utc.todo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private int views;

    @OneToMany(mappedBy = "customer")
    private List<Comment> comments;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Category category;

    private Date createDate;
    private Date updateDate;

    public Post() {
    }
}
