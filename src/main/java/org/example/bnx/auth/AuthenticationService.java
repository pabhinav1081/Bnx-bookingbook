package org.example.bnx.auth;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.example.bnx.Role.Role;
import org.example.bnx.Role.RoleRepo;
import org.example.bnx.email.EmailService;
import org.example.bnx.email.EmailTemplateName;
import org.example.bnx.user.Token;
import org.example.bnx.user.TokenRepo;
import org.example.bnx.user.User;
import org.example.bnx.user.userRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final userRepo userRepo;
    private final TokenRepo tokenRepo;

    @Value("${application.security.mailing.frontend.activation-url}")
    private String activationUrl;

   private final EmailService emailService;

    public void register(
            RegistrationRequest request
    ) throws MessagingException {
        var userRole= roleRepo.findByName("USER")
                .orElseThrow(()->new IllegalArgumentException("user role not found"));

        var user= User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole)).build();
        userRepo.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmail(),
                user.fullname(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken ,
                "Acoutn acitavete"

        );



    }

    private String generateAndSaveActivationToken(User user) {
       String generatToken= generateActivation(6);
       var token = Token.builder()
               .token(generatToken)
               .created(LocalDateTime.now())
               .expires(LocalDateTime.now().plusDays(16))
               .user(user)
               .build();
       tokenRepo.save(token);

        return generatToken;
    }

    private String generateActivation(int length) {
        String character="0123456789";
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomINdex= random.nextInt(character.length());
            sb.append(character.charAt(randomINdex));
        }
        return sb.toString();
    }


}
