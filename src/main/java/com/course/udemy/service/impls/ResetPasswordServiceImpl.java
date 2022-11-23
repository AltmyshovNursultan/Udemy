package com.course.udemy.service.impls;

import com.course.udemy.dao.ResetPasswordRepo;
import com.course.udemy.dao.UserRepo;
import com.course.udemy.mapper.PasswordMapper;
import com.course.udemy.model.entity.ResetPassword;
import com.course.udemy.model.entity.User;
import com.course.udemy.service.MailService;
import com.course.udemy.service.ResetPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResetPasswordServiceImpl implements ResetPasswordService {
    private final ResetPasswordRepo passwordRepo;
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final PasswordMapper passwordMapper;

    @Override
    public ResponseEntity<?> sendEmailForReset(String email) {
        Optional<User> user = userRepo.findUserByEmail(email);
        if (user.isEmpty())
            return ResponseEntity.badRequest().body(email+" email address does not exists!");
        String token = generateToken(email);
        ResetPassword resetPassword = passwordMapper.toResetPassword(user.get(),token);
        passwordRepo.save(resetPassword);
        mailService.sendMessage(email,"Reset Password","Click the link below to set up new password\n"
                +"http://localhost:8080/main/resetPassword/"+token);
        return ResponseEntity.ok("Check you email, you should get link.");
    }

    @Override
    public ResponseEntity<?> setUpNewPassword(String token, String resetCode, String confirmedCode) {
        token = URLEncoder.encode(token);
        Optional<ResetPassword> resetPassword = passwordRepo.findByToken(token);
        if (!isValidToken(resetPassword))
            return ResponseEntity.badRequest().body("Linke is expired!");
        User user = resetPassword.get().getUser();
        user.setPassword(passwordEncoder.encode(confirmedCode));
        userRepo.save(user);
        passwordRepo.delete(resetPassword.get());
        mailService.sendMessage(user.getEmail(),"Password successfully updated","Your password successfully updated");
        return ResponseEntity.ok("Password successfully updated");
    }
    private boolean isValidToken(Optional<ResetPassword> resetPassword){
        if (resetPassword.isEmpty()) return false;
        ResetPassword password =resetPassword.get();
        return !password.getGeneratedTime().plusHours(24).isBefore(LocalDateTime.now());
    }
    private String generateToken(String email){
        String token = passwordEncoder.encode(email);
        token = token.replace("/","");
        token = token.replace("\\","");
        return URLEncoder.encode(token);
    }
}
