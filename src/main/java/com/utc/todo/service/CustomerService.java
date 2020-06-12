package com.utc.todo.service;

import com.utc.todo.entity.Customer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);

    Customer save(Customer customer);

    Customer getByUsername(String username);
}
