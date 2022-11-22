package com.course.udemy.pojo.request;

import com.course.udemy.model.enums.Audience;
import com.course.udemy.model.enums.Experience;
import com.course.udemy.model.enums.Type;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    @NotNull
    String firstName;
    @NotNull
    String lastName;
    @NotNull
    String email;
    @NotNull
    String password;
    @NotNull
    Type type;
    Experience experience;
    Audience audience;
}
