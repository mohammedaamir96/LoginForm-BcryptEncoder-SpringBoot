package com.example.formlogin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.formlogin.pojo.UserRegistrationPOJO;

@Repository
public interface UserRepository extends JpaRepository<UserRegistrationPOJO, Long> {

    UserRegistrationPOJO findByUsername(String username);

}

