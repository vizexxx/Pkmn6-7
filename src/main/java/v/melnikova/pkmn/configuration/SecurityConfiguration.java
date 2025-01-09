package v.melnikova.pkmn.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import v.melnikova.pkmn.security.filters.JwtAuthentificationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService jdbcUserDetailsManager;
    private final JwtAuthentificationFilter jwtAuthentificationFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                customizer ->
                        customizer

                                .requestMatchers("/api/v1/cards/all").permitAll()
                                .requestMatchers("/api/v1/cards/owner").permitAll()
                                .requestMatchers("/api/v1/cards/name/{name}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/cards").hasRole("ADMIN").
                                requestMatchers(HttpMethod.POST, "/api/v1/students").hasRole("ADMIN")
                                .requestMatchers("/register").permitAll().
                                anyRequest().authenticated()

        );
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.formLogin(form -> form.successForwardUrl("/success"));
        http.userDetailsService(jdbcUserDetailsManager);
        http.addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
