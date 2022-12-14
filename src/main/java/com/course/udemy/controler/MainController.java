package com.course.udemy.controler;

import com.course.udemy.config.daoconfig.UserPrincipleDetailService;
import com.course.udemy.pojo.request.RegisterRequest;
import com.course.udemy.service.MainService;
import com.course.udemy.service.ResetPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
@CrossOrigin
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    private final UserPrincipleDetailService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final ResetPasswordService passwordService;

    @PostMapping("/register")
    public ResponseEntity<?> register(RegisterRequest request) throws IllegalAccessException {
        mainService.registerUser(request);
        return ResponseEntity.ok(request);
    }

    @GetMapping("login")
    public ResponseEntity<?> login(@RequestParam String email, String password) {
        return ResponseEntity.ok(mainService.login(email,password));
    }


    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(String email){
        return ResponseEntity.ok(passwordService.sendEmailForReset(email));
    }
    @PostMapping("/resetPassword/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable(name = "token") String token ,@RequestParam String resetCode,
                                           @RequestParam String confirmedCode){
        if (!resetCode.equals(confirmedCode)) return ResponseEntity.badRequest().body("You password is not coincide!");
        return passwordService.setUpNewPassword(token,resetCode,confirmedCode);
    }

}
