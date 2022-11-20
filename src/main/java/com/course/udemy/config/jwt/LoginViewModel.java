package com.course.udemy.config.jwt;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginViewModel {
    String email;
    String password;
}
