package com.utc.todo.service;

import com.utc.todo.dto.CustomUserDetail;
import com.utc.todo.entity.Customer;
import com.utc.todo.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements UserDetailsService {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Customer customer = customerRepo.findByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }
        CustomUserDetail customUserDetail = new CustomUserDetail(customer);
        return customUserDetail;
    }
}
