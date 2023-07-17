
package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
	
    private UserService userService;

    @Autowired
    public UserController(UserService userService ) {
        this.userService = userService;
    }
    
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model, HttpSession session) {
        User findUser = userService.getUser(user.getUsername(), user.getPassword());
        if (findUser != null) {
            session.setAttribute("loggedInUser", findUser); // 将用户保存到会话中
            return "redirect:/users/" + findUser.getId();
        } else {
            model.addAttribute("errorMessage", "帳號或密碼錯誤");
            return "login";
        }
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model, HttpSession session) {
        // 检查用户是否已登录
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getId() != id  ) {
            return "redirect:/users/login"; // 重定向到登录页面
        }

        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "user";
        } else {
            return "404";
        }
    }
    
    @GetMapping("/")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    
    @PostMapping("/create")
    @Transactional
    public String createUser(@ModelAttribute("user") User user,Model model) {
        if (userService.getUserByName(user.getUsername())) {
        	model.addAttribute("errorCreate", "帳號已被註冊");
    		return "login";
        } else {
            userService.saveUser(user);
        	return "redirect:/users/";
        }
    }
    
    
//    @PutMapping("/update/{id}")
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user,Model model) {
        User existingUser = userService.getUserById(id);
        System.out.println(id);
        if (existingUser != null) {
        	model.addAttribute("user", user);
            return "user";
        } else {
        	return "user";
        }
    }
    
    
//    @DeleteMapping("/delete/{id}")
    @PostMapping("/delete/{id}")
    @Transactional
    public String deleteUser(@PathVariable Long id) {
        User existingUser = userService.getUserById(id);
        if (existingUser != null) {
        	userService.deleteUser(id);
        	return "redirect:/users/";
        } else {
        	return "user";
        }
    }
    
}