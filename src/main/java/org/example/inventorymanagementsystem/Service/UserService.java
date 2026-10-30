package org.example.inventorymanagementsystem.Service;

import org.example.inventorymanagementsystem.dto.UserRequest;
import org.example.inventorymanagementsystem.model.Role;
import org.example.inventorymanagementsystem.model.User;
import org.example.inventorymanagementsystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.example.inventorymanagementsystem.Service.JwtService;

@Service
public class UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public User register(UserRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.STAFF);

        return userRepository.save(user);
    }

    public String login(String username, String password) {

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(authentication);

        return jwtService.generateToken(username);
    }

}
