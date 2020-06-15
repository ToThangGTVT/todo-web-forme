package com.utc.todo.service.impl;

import com.utc.todo.dto.CustomUserDetail;
import com.utc.todo.entity.Authority;
import com.utc.todo.entity.Customer;
import com.utc.todo.exception.EmailExitsException;
import com.utc.todo.exception.UsernameExitsException;
import com.utc.todo.repository.AuthorityRepoJPA;
import com.utc.todo.repository.CustomerRepoJPA;
import com.utc.todo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepoJPA customerRepo;
    private CustomUserDetail customUserDetail;

    @Autowired
    private AuthorityRepoJPA authorityRepo;

    @Autowired
    public CustomerServiceImpl(CustomerRepoJPA customerRepo) {
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
    @Transactional(rollbackOn = Exception.class)
    public Customer save(Customer customer) throws EmailExitsException, UsernameExitsException {
        if (checkEmailExits(customer.getEmail())) {
            throw new EmailExitsException();
        }
        if (checkUsernameExits(customer.getUsername())) {
            throw new UsernameExitsException();
        }
        Authority authority = authorityRepo.findByName("USER");
        customer.setAuthority(authority);
        return customerRepo.save(customer);
    }

    @Override
    public Customer getByUsername(String username) {
        return customerRepo.getByUsername(username);
    }

    private boolean checkEmailExits(String email) {
        if (email == null) {
            return false;
        }
        return customerRepo.getByEmail(email).size() != 0;
    }

    private boolean checkUsernameExits(String username) {
        return getByUsername(username) != null;
    }
}
