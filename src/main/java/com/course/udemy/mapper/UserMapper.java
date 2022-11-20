package com.course.udemy.mapper;

import com.course.udemy.config.daoconfig.UserPrinciple;
import com.course.udemy.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
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
}
