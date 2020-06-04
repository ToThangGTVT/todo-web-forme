package com.utc.todo.service;

import com.utc.todo.entity.Customer;
import com.utc.todo.entity.Post;
import com.utc.todo.exception.DateInputException;

import java.text.ParseException;

public interface PostService {

    Post save(String content, String strBegin, String strEnd, Customer customer) throws ParseException, DateInputException;
}
