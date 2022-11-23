package com.course.udemy.mapper;

import com.course.udemy.model.entity.ResetPassword;
import com.course.udemy.model.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class PasswordMapper {
    public ResetPassword toResetPassword(User user, String token){
        return ResetPassword.builder()
                .id(user.getId())
                .token(token)
                .generatedTime(LocalDateTime.now())
                .build();
    }
}
