package com.example.applicationtransportdescolis.Controllers;
import com.example.applicationtransportdescolis.Configuration.JwtUtils;
import com.example.applicationtransportdescolis.Repositories.UserRepositorie;
import com.example.applicationtransportdescolis.Services.TokenBlacklistService;
import com.example.applicationtransportdescolis.Services.UserService;
import com.example.applicationtransportdescolis.Entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepositorie userRepositorie;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final TokenBlacklistService tokenBlacklistService;


    public AuthController(UserRepositorie userRepositorie, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserService userService, JwtUtils jwtUtils, TokenBlacklistService tokenBlacklistService) {
        this.userRepositorie = userRepositorie;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepositorie.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepositorie.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            String token = jwtUtils.generateJwtToken(user.getUsername());

            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
            return ResponseEntity.ok("Déconnexion réussie.");
        }
        return ResponseEntity.badRequest().body("Token manquant.");
    }


    @GetMapping("/countUser")
    public List<User> countUser() {
        return userService.allUser();
    }
}
