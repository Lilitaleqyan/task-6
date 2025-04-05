package com.example.demo.services.imp;

import com.example.demo.dao.UserDao;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public List<User> readUser() {
        return userDao.readUser();

    }

    @Override
    public void updateUser(long id, String first_name, String last_name, int age, String email) {
            userDao.updateUser(id, first_name, last_name, age, email);
    }
    @Override
    public boolean dropUser(long id) {
        Optional<User> optionalUser = userDao.findUserById(id);
        if (optionalUser.isPresent()) {
            userDao.dropUser(id);
            return true;
        }
        return false;
    }

    @Override
    public User getByUser(User user) {
        return userDao.getByUser(user);
    }
}
