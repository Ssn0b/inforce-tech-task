package com.snob.tech.service;

import com.snob.tech.model.User;
import com.snob.tech.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User saveUser(User user) {
    String hashedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(hashedPassword);
    User savedUser = userRepository.save(user);
    log.info("User saved successfully: " + savedUser.getEmail());
    return savedUser;
  }

  public User findByEmail(String email) {
    User user = userRepository.findByEmail(email);
    if (user != null) {
      log.info("User found: " + user.getEmail());
    } else {
      log.info("User not found with email: " + email);
    }
    return user;
  }

  public boolean authenticateUser(String email, String password) {
    User user = userRepository.findByEmail(email);
    if (user != null && passwordEncoder.matches(password, user.getPassword())) {
      log.info("User authenticated: " + user.getEmail());
      return true;
    }
    log.info("Authentication failed for user with email: " + email);
    return false;
  }

  public User updateUser(User user) {
    User updatedUser = userRepository.save(user);
    log.info("User updated successfully: " + updatedUser.getEmail());
    return updatedUser;
  }
}
