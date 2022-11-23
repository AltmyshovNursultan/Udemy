package com.course.udemy.service;

public interface MailService {
    void sendMessage(String toEmail, String subject, String text);
}
