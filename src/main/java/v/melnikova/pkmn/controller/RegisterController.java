package v.melnikova.pkmn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import v.melnikova.pkmn.dto.UserDTO;

import java.util.List;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final JdbcUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public String registerUser(@RequestBody UserDTO userDto) {
        if (userDetailsManager.userExists(userDto.getUsername())) {
            return "Пользователь уже существует";
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setEnabled(true);

        org.springframework.security.core.userdetails.User user =
                new org.springframework.security.core.userdetails.User(
                        userDto.getUsername(),
                        userDto.getPassword(),
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
                );

        userDetailsManager.createUser(user);

        return "Пользователь зарегистрирован";
    }
}
