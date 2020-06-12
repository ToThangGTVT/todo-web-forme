package com.utc.todo.repository;


import com.utc.todo.dto.CustomerId;
import com.utc.todo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public interface CustomerRepoJPA extends JpaRepository<Customer, Serializable> {

    Customer findByUsername(String username);

    Customer getByUsername(String username);

    List<CustomerId> getByEmail(String email);

}
