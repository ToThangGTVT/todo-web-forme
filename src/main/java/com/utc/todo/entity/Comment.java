package com.utc.todo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Customer customer;

    private Date createDate;
    private Date updateDate;
}
