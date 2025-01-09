package v.melnikova.pkmn.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import v.melnikova.pkmn.security.jwt.JwtService;

import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {
    private final JwtService jwtService;

    private final LoginService loginService;

    private final JdbcUserDetailsManager jdbcUserDetailsManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws CredentialException {
        if (!jdbcUserDetailsManager.userExists(loginRequest.getUsername())) {
            return ResponseEntity.ok("User should be registered");
        }
        String jwt = loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(jwt);
    }



    @PostMapping("/success")
    public ResponseEntity<String> success(@AuthenticationPrincipal UserDetails user, HttpServletResponse response) throws IOException {
        log.info("Authentificated user {}", user.getUsername());
        String jwt = jwtService.createJwt(user.getUsername(), user.getAuthorities().iterator().next());
        log.info("Create jwt token for user {}", jwt);
        response.addCookie(new Cookie("jwt", Base64.getEncoder().encodeToString(jwt.getBytes(StandardCharsets.UTF_8))));
        ClassPathResource resource = new ClassPathResource("success.html");
        String success = new String(Files.readAllBytes(resource.getFile().toPath()));
        return ResponseEntity.ok()
                .body(success);
    }


}
