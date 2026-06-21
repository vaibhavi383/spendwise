package com.vaibhavi.spendwise.repository;

import java.util.List;   
import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhavi.spendwise.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByName(String name);
}