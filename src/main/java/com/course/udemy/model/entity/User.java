package com.course.udemy.model.entity;

import com.course.udemy.model.enums.Audience;
import com.course.udemy.model.enums.Experience;
import com.course.udemy.model.enums.Type;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    public User(String email, String password, boolean isMentor, String firstName, String lastName, Type type) {
        this.email = email;
        this.password = password;
        this.isMentor = isMentor;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
    }

    @Id
            @SequenceGenerator(
                name = "user_sequence",
                sequenceName = "user_sequence",
                allocationSize = 1
            )
            @GeneratedValue(
                    strategy = GenerationType.IDENTITY,
                    generator = "user_sequence"
            )
    Long id;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    Type type;
    @Enumerated(EnumType.STRING)
    Experience experience;
    @Enumerated(EnumType.STRING)
    Audience audience;
    boolean isMentor;
    String firstName;
    String lastName;

}
