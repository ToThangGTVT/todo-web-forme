package com.utc.todo.repository;


import com.utc.todo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Serializable> {
    Customer findByUsername(String username);
}
