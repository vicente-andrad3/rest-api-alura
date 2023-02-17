package med.vol.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Component genérico
public class SecurityFilter extends OncePerRequestFilter { // Herança do spring para realizar filters

    @Override // filterChain é a cadeia de filters
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { // Valida apenas uma vez por request


        filterChain.doFilter(request, response); // Chama os próximo filters, só usa caso queira ver outros filtros
    }

}
