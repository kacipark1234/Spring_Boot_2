# Spring_Boot_2

這是一個使用Spring Boot搭建的會員登錄系統練習，採用MVC框架進行設計和實現。該系統提供用戶註冊、登錄和註銷功能，並且只允許已註冊的用戶訪問系統的特定功能。
目前使用@Controller的方式導向網頁

# 功能
用戶註冊：用戶可以通過提供用戶名、密碼和電子郵件地址進行註冊  
用戶登錄：已註冊的用戶可以使用其帳密登錄系統  
用戶刪除：用戶可以從系統中刪除帳號(軟刪除)  

# 技術
Java  
Spring Boot  
Spring MVC  
Thymeleaf（作為模板引擎）  
HTML/CSS（用於前端界面）  
MySQL（用於存儲用戶數據）  

# 使用環境
Eclipse  

# 資料庫配置

```
spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

# 畫面顯示

## login.html

### 登入畫面
![image](https://github.com/kacipark1234/Spring_Boot_2/assets/93324400/1a1cf485-fe8d-4fbe-8d96-d92190687db9)

### 新增帳號
![image](https://github.com/kacipark1234/Spring_Boot_2/assets/93324400/5a7faf3b-823e-4e9a-ab33-b0e3f4a315c7)

## users.html

### 顯示所有帳號資料
![image](https://github.com/kacipark1234/Spring_Boot_2/assets/93324400/0166d023-88ee-4f74-9645-a9e0ca338eaf)

## user.html

### 成功登入畫面
![image](https://github.com/kacipark1234/Spring_Boot_2/assets/93324400/559aeef1-408d-4092-bba5-dd89b3136c39)

### 編輯畫面
![image](https://github.com/kacipark1234/Spring_Boot_2/assets/93324400/2df9870f-5dbc-4f90-9f9a-bbba9ace4046)

### 刪除確認
![image](https://github.com/kacipark1234/Spring_Boot_2/assets/93324400/4d30ff39-c61a-405b-b98f-73a55cd12011)


## MySQL
```SQL
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  enabled BOOLEAN NOT NULL
);
```
![image](https://github.com/kacipark1234/Spring_Boot_2/assets/93324400/0d6393ef-7907-4a8a-89bb-25e2ce315c8e)

RESTful
```
package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        // 返回登录页面的HTML文件名，例如login.html
        return "login.html";
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User findUser = userService.getUser(user.getUsername(), user.getPassword());
        if (findUser != null) {
        	
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userService.getUserByName(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        } else {
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        System.out.println("Get");
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
    	System.out.println("Put");
    	User existingUser = userService.getUserById(id);
        if (existingUser != null) {
            user.setId(id);
            userService.updateUser(user);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    	System.out.println("Delete");
    	User existingUser = userService.getUserById(id);
        if (existingUser != null) {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

```
