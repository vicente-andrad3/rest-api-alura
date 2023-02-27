package med.vol.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))) // Configurar um Header do JWT
                .info(new Info()
                        .title("Voll.med API")
                        .description("API Rest da aplicação Voll.med, contendo as funcionalidade de CRUD de médico e paciente, além do agendamento  cancelamento de consultas")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://vol.med/api/licenca"))
                        .contact(new Contact()
                                .name("Vicente Andrade")
                                .email("vicenteandrad.cod3@gmail.com")));


    }
}
