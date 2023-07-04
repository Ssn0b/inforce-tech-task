package com.snob.tech.controller;

import com.snob.tech.model.User;
import com.snob.tech.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null) {
            model.addAttribute("existingUser", existingUser);
            model.addAttribute("message", "User with the same email already exists");
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }


    @PostMapping("/login")
    public String authenticateUser(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("email", user.getEmail());
        if (userService.authenticateUser(user.getEmail(), user.getPassword())) {
            return "redirect:/home";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/home")
    public String showHomePage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        User user = userService.findByEmail(email);

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute("user") User updatedUser, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        User user = userService.findByEmail(email);

        if (user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            userService.updateUser(user);
        }

        return "redirect:/home";
    }
}
