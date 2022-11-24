package com.course.udemy.dao;

import com.course.udemy.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    void findUserByEmail(){
       User user = userRepo.findUserByEmail("email").get();
       Assertions.assertThat(user.getEmail()).isEqualTo("email");
    }
}