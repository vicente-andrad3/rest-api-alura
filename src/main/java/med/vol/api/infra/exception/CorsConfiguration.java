package med.vol.api.infra.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    // OBSERVAÇÃO: PARA RESOLUÇÂO DE PROBLEMAS COM O CORS
    // NÃO SERÁ UTILIZADO NESSE PROJETO, APENAS PARA TESTE
    // PARA HABILITAR PARA TODAS URL, BASTA ADICIONAR * NO *allowedOrigins*
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
}

