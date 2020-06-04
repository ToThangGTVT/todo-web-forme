package com.utc.todo.service;

import com.utc.todo.entity.Customer;
import com.utc.todo.entity.Post;
import com.utc.todo.exception.DateInputException;

import java.text.ParseException;
import java.util.List;

public interface PostService {

    List<Post> getAll(Customer customer);

    Post save(String content, String strBegin, String strEnd, Customer customer, String title) throws ParseException, DateInputException;
}
