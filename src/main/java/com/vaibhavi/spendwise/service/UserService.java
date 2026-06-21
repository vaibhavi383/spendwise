package com.vaibhavi.spendwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhavi.spendwise.entity.User;
import com.vaibhavi.spendwise.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public User updateUser(Long id, User updatedUser) {

        User user = userRepository.findById(id).orElse(null);

        if(user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());

            return userRepository.save(user);
        }

        return null;
    }
}