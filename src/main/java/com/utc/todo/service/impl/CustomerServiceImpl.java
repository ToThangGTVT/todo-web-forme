package com.utc.todo.service.impl;

import com.utc.todo.dto.CustomUserDetail;
import com.utc.todo.entity.Authority;
import com.utc.todo.entity.Customer;
import com.utc.todo.repository.AuthorityRepo;
import com.utc.todo.repository.CustomerRepo;
import com.utc.todo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private CustomUserDetail customUserDetail;

    @Autowired
    private AuthorityRepo authorityRepo;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Customer customer = customerRepo.findByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }
        this.customUserDetail = new CustomUserDetail(customer);
        return this.customUserDetail;
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        Authority authority = authorityRepo.findByName("USER");
        customer.setAuthority(authority);
        return customerRepo.save(customer);
    }

    @Override
    public Customer getByUsername(String username) {
        return customerRepo.getByUsername(username);
    }
}
