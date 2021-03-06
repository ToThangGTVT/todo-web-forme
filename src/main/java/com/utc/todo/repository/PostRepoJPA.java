package com.utc.todo.repository;

import com.utc.todo.entity.Customer;
import com.utc.todo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface PostRepoJPA extends JpaRepository<Post, Serializable> {
    List<Post> getAllByCustomer(Customer customer);

    Post getByIdAndCustomer(int id, Customer customer);
}
