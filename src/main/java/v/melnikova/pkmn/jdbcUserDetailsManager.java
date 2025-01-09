package v.melnikova.pkmn;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class jdbcUserDetailsManager {

    private final UserDetailsService userDetailsService;
}
