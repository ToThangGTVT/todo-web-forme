package com.utc.todo.service.impl;

import com.utc.todo.entity.Authority;
import com.utc.todo.repository.AuthorityRepo;
import com.utc.todo.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private AuthorityRepo authorityRepo;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepo authorityRepo) {
        this.authorityRepo = authorityRepo;
    }

    @Override
    public Authority getAuthorityByName(String name) {
        return null;
    }
}
