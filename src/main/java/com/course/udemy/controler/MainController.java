package com.course.udemy.controler;

import com.course.udemy.Jwt2.AuthenticateRequest;
import com.course.udemy.Jwt2.AuthenticationResponse;
import com.course.udemy.Jwt2.JwtUtil;
import com.course.udemy.config.daoconfig.UserPrincipleDetailService;
import com.course.udemy.pojo.request.RegisterRequest;
import com.course.udemy.service.MainService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
@CrossOrigin
public class MainController {
    private final MainService mainService;

    private final UserPrincipleDetailService userDetailsService;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public MainController(MainService mainService, UserPrincipleDetailService userDetailsService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.mainService = mainService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(RegisterRequest request) throws IllegalAccessException {
        mainService.registerUser(request);
        return ResponseEntity.ok(request);
    }

    @GetMapping("login")
    public ResponseEntity<?> login(@RequestParam String email, String password) {
        return ResponseEntity.ok(mainService.login(email,password));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequest request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect credentials"+e);
        }
        final UserDetails userDetails1 = userDetailsService.loadUserByUsername(request.getEmail());
        final String token = jwtUtil.generateToken(userDetails1);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
