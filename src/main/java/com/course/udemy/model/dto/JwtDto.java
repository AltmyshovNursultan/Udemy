package com.course.udemy.model.dto;

import   com.course.udemy.model.enums.Type;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Type role;

    private String token;

    private String tokenType;
}
