package med.vol.api.infra.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.vol.api.domain.models.UsuarioModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UsuarioModel usuario) {
        try {
            var algorithm = Algorithm.HMAC256(secret); // Assinatura do token
            return JWT.create()
                    .withIssuer("API Voll.med") // identificador a qual aplicação responsável pela criação do token
                    .withSubject(usuario.getLogin()) // identificar o objeto que acessará o token
                    .withExpiresAt(dataExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw  new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT){ // Responsável por validar o token e retorna o subject(Usuario.getLogin())
        try {
            var algorithm = Algorithm.HMAC256(secret); // Assinatura do token
            return JWT.require(algorithm)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }

    private Instant dataExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); // Configuração para 2 horas de duração
    }

}
