package com.example.banksecurity.Repository;

import com.example.banksecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {
    User findUserByUsername(String userName);

    User findUserById(Integer id);
}
