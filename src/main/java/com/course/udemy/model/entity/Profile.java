package com.course.udemy.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    String competence;
    String language;
    String siteUrl;
    String twitterUrl;
    String facebookUrl;
    String linkedinUrl;
    String youtubeUrl;
    String image;
    boolean isHidden;
    boolean isHiddenCourse;
    boolean promotions;
    boolean mentorAds;
    boolean emailAds;
}
