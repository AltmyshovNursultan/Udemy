package com.course.udemy.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FirstStageRequestMentor {
    String firstName;
    String lastName;
    String email;
    String password;


}
