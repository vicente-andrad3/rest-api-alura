package med.vol.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.vol.api.domain.dto.medico.DadosAtualizarMedico;
import med.vol.api.domain.dto.medico.DadosCadastroMedico;
import med.vol.api.domain.dto.medico.DadosDetalhamentoMedico;
import med.vol.api.domain.dto.medico.DadosListagemMedico;
import med.vol.api.domain.models.MedicoModel;
import med.vol.api.domain.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired // Quem instancia é o próprio java, injeção de dependências
    private MedicoRepository repository;

    // Código 201 - CREATED
    // É necessário seguir algumas regras:
    // Devolver no corpo da resposta os dados do novo registro
    // Devolver o cabeçalho do protocolo HTTP(Location) - Serve para o front-end consiga acessar esse recurso
    @PostMapping
    @Transactional // Transação ativa com o BD
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) { // URIComponenteBuilder criar a uri que será acessada
        var medico = new MedicoModel(dados); // Variável de inicialização do model
        repository.save(medico); // Salvando a entity no banco
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri(); // Criando a uri do projeto + adicionando o id da entity criada
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico)); // Response 201 + uri da aplication + body com o conteúdo da entity
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"name"}) Pageable paginacao){ // Customização do Page
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new); // Conversão MedicoModel para o DTO de DadosListagemMedico
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid DadosAtualizarMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirMedico(@PathVariable Long id) { // Exclusão total // ResponseEntity é responsável pelas respostas dados pelas requisições HTTP
        var medico = repository.getReferenceById(id);
        medico.excluirMedico(); // Exclusão lógica
        return ResponseEntity.noContent().build(); // .build() Cria um objeto response entity
    }

    @GetMapping("/{id}") // Método de detalhar médico
    public ResponseEntity detalharMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
