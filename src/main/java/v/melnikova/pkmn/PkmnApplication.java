package v.melnikova.pkmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class PkmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(PkmnApplication.class, args); // Обязательный метод
    }
}