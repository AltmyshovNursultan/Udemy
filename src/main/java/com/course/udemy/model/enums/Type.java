package com.course.udemy.model.enums;


import com.course.udemy.pojo.request.RegisterRequest;

public enum Type {
    ROLE_ADMIN {
        @Override
        public void castByType(RegisterRequest registerForm) throws IllegalAccessException{

        }
    },
    ROLE_MENTOR{
        @Override
        public void castByType(RegisterRequest registerRequest) throws IllegalArgumentException {
            var experience = registerRequest.getExperience();
            var audience = registerRequest.getAudience();
            if (experience == null || audience == null)
                throw new IllegalArgumentException("Experience and audience are required!");
        }
    },
    ROLE_USER {
        @Override
        public void castByType(RegisterRequest registerRequest) throws IllegalArgumentException {
            registerRequest.setAudience(null);
            registerRequest.setExperience(null);
        }
    };

    public abstract void castByType(RegisterRequest registerForm) throws IllegalAccessException;
}
