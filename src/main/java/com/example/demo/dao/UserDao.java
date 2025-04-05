package com.example.demo.dao;

import com.example.demo.models.User;

import java.util.List;
import java.util.Optional;


public interface UserDao  {
    void createUser(User user);
    List<User> readUser();
    void updateUser(long id, String first_name, String last_name, int age, String email);
    void dropUser(Long id);
    Optional<User> findUserById(long id);

    User getByUser(User user);

}
