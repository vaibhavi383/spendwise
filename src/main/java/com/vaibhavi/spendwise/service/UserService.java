package com.vaibhavi.spendwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vaibhavi.spendwise.exception.UserNotFoundException;
import com.vaibhavi.spendwise.entity.User;
import com.vaibhavi.spendwise.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already registered");
        }

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(user.getPassword()));

        // Save user
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + id));
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
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }
}