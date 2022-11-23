package com.course.udemy.service;

import org.springframework.http.ResponseEntity;

public interface ResetPasswordService {
    ResponseEntity<?> sendEmailForReset(String email);

    ResponseEntity<?> setUpNewPassword(String token, String resetCode, String confirmedCode);
}
