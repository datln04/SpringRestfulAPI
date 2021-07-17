package com.example.spring.repository;

import com.example.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUser extends JpaRepository<User,Integer> {
    public User findByUserName(String userName);
    public User findByUserNameAndUserPassword(String userName, String userPassword);
}
