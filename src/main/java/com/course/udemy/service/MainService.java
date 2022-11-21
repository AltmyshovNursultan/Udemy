package com.course.udemy.service;

import com.course.udemy.model.dto.UserDto;
import com.course.udemy.request.RegisterRequest;

public interface MainService {
    UserDto registerUser(RegisterRequest request) throws IllegalAccessException;

    UserDto login(String email, String password);
}
