package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;


@Service
public interface UserService {
	
    Boolean getUserByName(String username);
    
    User getUser(String username, String password);

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

	User getUserById(Long id);
	
}