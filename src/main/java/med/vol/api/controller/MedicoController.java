package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.data.DadosCadastroMedico;
import med.vol.api.data.DadosListagemMedico;
import med.vol.api.models.MedicoModel;
import med.vol.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired // Quem instancia é o próprio java, injeção de dependências
    private MedicoRepository repository;

    @PostMapping
    @Transactional // Transação ativa com o BD
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new MedicoModel(dados));
    }

    @GetMapping
    public List<DadosListagemMedico> listar(){
        return repository.findAll().stream().map(DadosListagemMedico::new).toList(); // Conversão MedicoModel para o DTO de DadosListagemMedico
    }

}
