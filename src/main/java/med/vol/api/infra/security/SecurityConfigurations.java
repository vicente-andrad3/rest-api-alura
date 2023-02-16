package med.vol.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Indica que vamos personalizar as configurações de segurança
public class SecurityConfigurations {

    @Bean // Expor o return desse método, devolve um objeto para o spring ou um objeto que será injetado em algum local
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // É desabilitado o csrf , pois o token já fica responsável por proteger desse ataque
        // Depois modificamos o tipo de sessão para STATELESS seguindo a ideia do API REST
        // Por fim, damos o build() para criar essas modificações.
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager(); // Sabe criar objeto de manager
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
