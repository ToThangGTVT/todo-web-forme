//package com.utc.todo.dto;
//
//import com.utc.todo.entity.Customer;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//
//public class CustomUserDetail implements UserDetails {
//
//    private Customer customer;
//
//    public CustomUserDetail(Customer customer) {
//        this.customer = customer;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singleton(new SimpleGrantedAuthority(customer.getAuthority().getName()));
//    }
//
//    @Override
//    public String getPassword() {
//        return customer.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return customer.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//}
