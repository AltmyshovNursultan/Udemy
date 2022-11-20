package com.course.udemy.dao;

import com.course.udemy.model.entity.User;
import com.course.udemy.model.enums.Type;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserInit implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        userRepo.deleteAll();
            User user = new User("assat", passwordEncoder.encode("123"),
                false,"a","b",Type.ROLE_ADMIN);
        User mentor = new User("aat", passwordEncoder.encode("321"),
                false,"c","d",Type.ROLE_MENTOR);
        userRepo.save(mentor);
        userRepo.save(user);
    }
}
