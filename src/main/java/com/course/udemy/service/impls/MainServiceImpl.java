package com.course.udemy.service.impls;

import com.course.udemy.config.daoconfig.UserPrinciple;
import com.course.udemy.config.jwt.UtilJwt;
import com.course.udemy.dao.UserRepo;
import com.course.udemy.mapper.UserMapper;
import com.course.udemy.model.dto.JwtDto;
import com.course.udemy.model.dto.UserDto;
import com.course.udemy.model.entity.User;

import com.course.udemy.pojo.request.RegisterRequest;
import com.course.udemy.service.MainService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UtilJwt utilJwt;

    public MainServiceImpl(UserRepo userRepo, UserMapper userMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UtilJwt utilJwt) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.utilJwt = utilJwt;
    }

    @Override
    public UserDto registerUser(RegisterRequest request) throws IllegalAccessException {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.getType().castByType(request);
        User user = userRepo.save(userMapper.toUser(request));
        return userMapper.toUserDto(user);
    }

    @Override
    public JwtDto login(String email, String password) {
        Authentication authentication = getAuthenticationFromUser(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = utilJwt.generateToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return userMapper.toUserPrinciple(userPrinciple,token);
    }

    private Authentication getAuthenticationFromUser(String email, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
    }
}
