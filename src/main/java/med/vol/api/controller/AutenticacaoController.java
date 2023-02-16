package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.domain.dto.usuario.DadosAutenticacao;
import med.vol.api.domain.models.UsuarioModel;
import med.vol.api.infra.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager; // Responsável por realizar as autenticação do usuário

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.password()); // converte o dto record, no dto de authetication token
        var authentication = manager.authenticate(token); // recebe o dto convertido e dispara o processo de autenticação
        return ResponseEntity.ok(tokenService.generateToken((UsuarioModel) authentication.getPrincipal())); // Casting para dar os dados do usuário
    }

}
