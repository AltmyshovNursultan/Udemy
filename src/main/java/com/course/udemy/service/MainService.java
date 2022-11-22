package com.course.udemy.service;

import com.course.udemy.model.dto.JwtDto;
import com.course.udemy.model.dto.UserDto;
import com.course.udemy.pojo.request.RegisterRequest;

public interface MainService {
    UserDto registerUser(RegisterRequest request) throws IllegalAccessException;

    JwtDto login(String email, String password);
}
