package az.project.usermanagementsystem.service;

import az.project.usermanagementsystem.dao.entity.ERole;
import az.project.usermanagementsystem.dao.entity.RoleEntity;
import az.project.usermanagementsystem.dao.entity.UserEntity;
import az.project.usermanagementsystem.dao.repository.RoleRepository;
import az.project.usermanagementsystem.dao.repository.UserRepository;
import az.project.usermanagementsystem.dto.request.LoginRequest;
import az.project.usermanagementsystem.dto.request.SignUpRequest;
import az.project.usermanagementsystem.dto.response.JwtResponse;
import az.project.usermanagementsystem.security.JwtUtils;
import az.project.usermanagementsystem.security.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder encoder,
                       JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        log.info("Auth.login start for user: {}", loginRequest.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JwtResponse response = new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);

        log.info("Auth.login end for user: {}", loginRequest.getUsername());

        return response;
    }

    public void registerUser(SignUpRequest signUpRequest) {
        log.info("Auth.register start for user: {}", signUpRequest.getUsername());

        if (userRepository.existsByUsername(signUpRequest.getUsername()))
            throw new RuntimeException("Error: Username is already taken!");

        if (userRepository.existsByEmail(signUpRequest.getEmail()))
            throw new RuntimeException("Error: Email is already exist!");

        // Create new user's account
        UserEntity user = UserEntity.builder()
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .username(signUpRequest.getUsername())
                .build();

        Set<String> strRoles = signUpRequest.getRoles();

        Set<RoleEntity> roles = new HashSet<>();

        if (strRoles == null) {
            RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("admin".equals(role)) {
                    RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        log.info("Auth.register end for user: {}", signUpRequest.getUsername());
    }
}