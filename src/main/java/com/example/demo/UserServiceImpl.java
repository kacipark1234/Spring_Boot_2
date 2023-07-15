package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //搜尋有無帳號資料(帳號)
    @Override
    public Boolean getUserByName(String username) {
        return (userRepository.findByUsername(username) != null);
    }
    
    //搜尋有無帳號資料(帳號,密碼)
    @Override
    public User getUser(String username ,String password) {
        return userRepository.findUser(username, password);
    }
    
    //取得所有資料庫資料
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(); 
    }

    //創建新資料
    @Override
    public void saveUser(User user) {
    	userRepository.saveUser(user.getUsername(),user.getPassword(),user.getEmail());
    }

    //更改帳號資料
    @Override
    public void updateUser(User user) {
    	
        userRepository.updateUser(user.getId(),user.getUsername(),user.getPassword(),user.getEmail());
    }

    //刪除帳號資料(軟刪除)
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }
}