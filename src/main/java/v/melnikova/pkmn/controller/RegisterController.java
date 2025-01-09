package v.melnikova.pkmn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import v.melnikova.pkmn.dto.UserDTO;
import v.melnikova.pkmn.service.UserService;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegistrationService registrationService;

    private final JdbcUserDetailsManager jdbcUserDetailsManager;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest loginRequest) {
        registrationService.registerUser(loginRequest);
        return ResponseEntity.ok("User registered successfully");
    }
}
