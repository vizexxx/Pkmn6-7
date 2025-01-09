package v.melnikova.pkmn.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import v.melnikova.pkmn.security.jwt.JwtService;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthentificationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws SecurityException, IOException, ServletException {

        logger.info(String.valueOf(request.getHeaderNames()));

        String jwtToken = request.getHeader("Authorization");

        log.info("JWT токен получен из заголовка {}",jwtToken);

        if(Objects.isNull(jwtToken) || jwtToken.startsWith("Bearer"))
        {
            for(Cookie cookie : request.getCookies())
            {
                if(cookie.getName().equals("jwt"))
                {
                    jwtToken = new String(Base64.getDecoder().decode(cookie.getValue()));
                    log.info("JWT токен найден из cookie");
                }
            }
            if (Objects.isNull(jwtToken))
            {
                log.info("JWT токен не найден");
                filterChain.doFilter(request,response);
                return;
            }
        }
        if (jwtToken.startsWith("Bearer"))
        {
           jwtToken = jwtToken.split("Bearer ")[0];
        }

        log.info("Получен jwt токен {}",jwtToken);

        DecodedJWT decodedJWT =jwtService.verify(jwtToken);

        if (Objects.isNull(decodedJWT)) {
            log.info("Недействительный jwt токен");
            filterChain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        decodedJWT.getSubject(),
                        null,
                        decodedJWT.getClaim("authorities").asList(SimpleGrantedAuthority.class)
                )
        );

        filterChain.doFilter(request, response);
    }

}
