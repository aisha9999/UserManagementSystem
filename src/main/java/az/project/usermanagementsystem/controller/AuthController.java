package az.project.usermanagementsystem.controller;

import az.project.usermanagementsystem.dto.request.LoginRequest;
import az.project.usermanagementsystem.dto.request.SignUpRequest;
import az.project.usermanagementsystem.dto.response.JwtResponse;
import az.project.usermanagementsystem.dto.response.MessageResponse;
import az.project.usermanagementsystem.service.AuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signIn")
    public JwtResponse authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signUp")
    public MessageResponse registerUser(@Validated @RequestBody SignUpRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }
}


