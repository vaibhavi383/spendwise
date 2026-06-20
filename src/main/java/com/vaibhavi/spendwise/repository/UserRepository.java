package com.vaibhavi.spendwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhavi.spendwise.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}