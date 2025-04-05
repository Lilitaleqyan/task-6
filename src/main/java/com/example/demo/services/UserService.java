package com.example.demo.services;

import com.example.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void createUser(User user);
    List<User> readUser();
    void updateUser(long id, String first_name, String last_name, int age, String email);
    boolean dropUser(long id);

}
