package com.utc.todo.service;

import com.utc.todo.dto.CustomUserDetail;
import com.utc.todo.entity.Customer;
import com.utc.todo.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public interface CustomerService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);

    Customer save(Customer customer);
}
