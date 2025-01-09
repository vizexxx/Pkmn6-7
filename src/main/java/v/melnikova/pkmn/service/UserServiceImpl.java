package v.melnikova.pkmn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import v.melnikova.pkmn.dto.UserDTO;

import javax.security.auth.login.CredentialException;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final JdbcUserDetailsManager jdbcUserDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String loginUser(UserDTO userDTO) throws CredentialException {
        if (!jdbcUserDetailsManager.userExists(userDTO.getUsername())) {
            throw new CredentialException("User does not exist!");
        }
        return "Login successful for user: " + userDTO.getUsername();
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        if (jdbcUserDetailsManager.userExists(userDTO.getUsername())) {
            throw new IllegalArgumentException("User already exists!");
        }

        UserDetails user = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles("USER") // ROLE_USER по умолчанию
                .build();

        jdbcUserDetailsManager.createUser(user);
    }
}
