package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import java.util.List;

@Controller
@SessionAttributes({"username", "password"})
public class UserController {
    private UserService userService;

    public UserController(UserService userService ) {
        this.userService = userService;
    }
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    
    @PostMapping("/login")
    @Transactional
    public String FindUser(Model model , @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        
    	User findUser = userService.getUser(user.getUsername(), user.getPassword());
    	if (findUser != null) {
        	model.addAttribute("user", findUser);
            return "data";
        } else {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/login";
        }
    }
    
    @GetMapping("/data")
    public String getData() {
        return "login";
    }
    
    @GetMapping("/update")
    public String update() {
        return "login";
    }
    
    @PostMapping("/update")
    @Transactional
    public String updateUser(Model model ,@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userService.updateUser(user);
        User findUser = userService.getUser(user.getUsername(), user.getPassword());
        model.addAttribute("user", findUser);
        
        return "/data";
    }
    
    @GetMapping("/delete")
    public String delete() {
        return "login";
    }
    
    @PostMapping("/delete")
    @Transactional
    public String deleteUser(Model model ,@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
    	System.out.println("1");
    	System.out.println(user);
        userService.deleteUser(user.getId());
        return "redirect:/users";
    }
    
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    
    @GetMapping("/create")
    public String showCreateAccountPage(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }
    
    @PostMapping("/create")
    @Transactional
    public String createAccount(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
    	
    	//檢查資料庫是否有此資料
    	if(userService.getUserByName(user.getUsername())) {
    		redirectAttributes.addAttribute("error", true);
    		return "redirect:/create";
    	}else {
    		//創建(username ,password ,email)資料
    		userService.saveUser(user);
    		return "redirect:/users";
    	}
    	
    }

    
    
}