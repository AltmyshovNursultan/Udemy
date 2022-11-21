package com.course.udemy.service.impls;

import com.course.udemy.dao.UserRepo;
import com.course.udemy.mapper.UserMapper;
import com.course.udemy.model.dto.UserDto;
import com.course.udemy.model.entity.User;
import com.course.udemy.request.RegisterRequest;
import com.course.udemy.service.MainService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public MainServiceImpl(UserRepo userRepo, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto registerUser(RegisterRequest request) throws IllegalAccessException {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.getType().castByType(request);
        User user = userRepo.save(userMapper.toUser(request));
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto login(String email, String password) {
        User user = userRepo.findUserByEmail(email).orElseThrow(()->new NullPointerException("Null"));
        if (user.getPassword()!= passwordEncoder.encode(password))
            throw new IllegalStateException("Credential is not correct");
        return userMapper.toUserDto(user);
    }
}
