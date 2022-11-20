package com.course.udemy.config.daoconfig;

import com.course.udemy.dao.UserRepo;
import com.course.udemy.mapper.UserMapper;
import com.course.udemy.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipleDetailService implements UserDetailsService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserPrincipleDetailService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(username).orElseThrow(()->  new NullPointerException("Null"));
        return userMapper.toUserDetails(user);
    }
}
