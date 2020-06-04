package com.utc.todo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "text")
    private String content;

    private Date startDate;
    private Date endDate;
    private Date createDate;
    private Date updateDate;
    private String status;

    @ManyToOne
    private Customer customer;
}
