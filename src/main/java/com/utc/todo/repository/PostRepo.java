package com.utc.todo.repository;

import com.utc.todo.entity.Customer;
import com.utc.todo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface PostRepo extends JpaRepository<Post, Serializable> {
    List<Post> getAllByCustomer(Customer customer);

    Post getById(int id);
}
