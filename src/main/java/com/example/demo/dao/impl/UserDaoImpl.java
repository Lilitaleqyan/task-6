package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
         entityManager.persist(user);

    }

    @Override
    public List<User> readUser(){
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public void updateUser(long id, String first_name, String last_name,int age, String email) {
        User user = entityManager.find(User.class, id);
        if(user != null) {
            user.setFirst_name(first_name);
            user.setLast_name(last_name);
            user.setAge(age);
            user.setEmail(email);
            entityManager.merge(user);
        }
    }
    @Override
    public void dropUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public Optional<User> findUserById(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }
}

