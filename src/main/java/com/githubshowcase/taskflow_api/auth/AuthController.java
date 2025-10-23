package com.githubshowcase.taskflow_api.auth;


import com.githubshowcase.taskflow_api.security.JwtService;
import com.githubshowcase.taskflow_api.user.UserEntity;
import com.githubshowcase.taskflow_api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@RequestBody AuthRequest req) {
        if (userRepo.findByUsername(req.username()).isPresent()){
            throw new RuntimeException("User already exists");
        }
        var user = UserEntity.builder()
                .username(req.username())
                .password(passwordEncoder.encode(req.password()))
                .role(UserEntity.Role.USER)
                .build();
        userRepo.save(user);
        return new AuthResponse(jwtService.generateToken(req.username()));
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );
        return new AuthResponse(jwtService.generateToken(req.username()));
    }
}