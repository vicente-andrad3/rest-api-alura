package med.vol.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.vol.api.domain.repository.UsuarioRepository;
import med.vol.api.infra.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Component genérico
public class SecurityFilter extends OncePerRequestFilter { // Herança do spring para realizar filters
    // API REST - Conceito Stateless: Não há um login salvo.
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override // filterChain é a cadeia de filters
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { // Valida apenas uma vez por request

        var tokenJWT = recorverToken(request); // Recuperar o token do cabeçalho
        if(tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT); // Autentica o token e retorna o subject - usuário
            var usuario = repository.findByLogin(subject); // Recuperei o o objeto usuário

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); // Autentica o usuario
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response); // Chama os próximo filters, só usa caso queira ver outros filtros
    }

    private String recorverToken(HttpServletRequest request) { // Recuperar o token do cabeçalho
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", ""); // Retirar o Bearer
        }
        return null;
    }
}
