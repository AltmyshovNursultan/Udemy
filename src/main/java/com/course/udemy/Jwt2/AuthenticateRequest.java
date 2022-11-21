package com.course.udemy.Jwt2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticateRequest {
    String email;
    String password;

}
