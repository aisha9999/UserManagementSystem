package az.project.usermanagementsystem.controller;

import az.project.usermanagementsystem.dto.request.LoginRequest;
import az.project.usermanagementsystem.dto.request.UserRequest;
import az.project.usermanagementsystem.dto.response.JwtResponse;
import az.project.usermanagementsystem.service.AuthService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public JwtResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public void registerUser(@RequestBody UserRequest userRequest) {
        authService.registerUser(userRequest);
    }
}


