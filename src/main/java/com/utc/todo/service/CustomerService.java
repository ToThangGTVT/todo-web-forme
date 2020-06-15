package com.utc.todo.service;

import com.utc.todo.entity.Customer;
import com.utc.todo.exception.EmailExitsException;
import com.utc.todo.exception.UsernameExitsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);

    Customer save(Customer customer) throws EmailExitsException, UsernameExitsException;

    Customer getByUsername(String username);

}
