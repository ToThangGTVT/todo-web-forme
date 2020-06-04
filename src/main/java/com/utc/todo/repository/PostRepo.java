package com.utc.todo.repository;

import com.utc.todo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PostRepo extends JpaRepository<Post, Serializable> {

}
