package com.utc.todo.repository;

import com.utc.todo.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Serializable> {
    Authority findByName(String name);
}
