package com.course.udemy.mapper;

import com.course.udemy.config.daoconfig.UserPrinciple;
import com.course.udemy.model.dto.JwtDto;
import com.course.udemy.model.dto.UserDto;
import com.course.udemy.model.entity.User;
import com.course.udemy.model.enums.Type;
import com.course.udemy.pojo.request.RegisterRequest;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserDetails toUserDetails(User user) {
        return UserPrinciple.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .type(user.getType())
                .experience(user.getExperience())
                .audience(user.getAudience())
                .isMentor(user.isMentor())
                .build();
    }

    public User toUser(RegisterRequest registerForm) {
        var user = User.builder()
                .firstName(registerForm.getFirstName())
                .lastName(registerForm.getLastName())
                .email(registerForm.getEmail())
                .password(registerForm.getPassword())
                .type(registerForm.getType())
                .experience(registerForm.getExperience())
                .audience(registerForm.getAudience())
                .build();
        if (user.getType() == Type.ROLE_MENTOR) user.setMentor(true);
        return user;
    }
    public UserDto toUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .type(user.getType())
                .build();
    }
    public JwtDto toUserPrinciple(UserPrinciple userDetails, String token){
        return JwtDto.builder()
                .id(userDetails.getId())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .email(userDetails.getEmail())
                .token(token)
                .role(userDetails.getType())
                .tokenType(token)
                .build();
    }
}
