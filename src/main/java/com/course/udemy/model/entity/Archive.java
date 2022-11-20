package com.course.udemy.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "profiles")
public class Archive {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}
