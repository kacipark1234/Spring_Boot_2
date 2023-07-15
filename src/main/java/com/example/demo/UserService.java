package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	
    Boolean getUserByName(String username);
    
    User getUser(String username, String password);

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);
	
}