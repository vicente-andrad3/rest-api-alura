package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.models.DadosCadastroMedico;
import med.vol.api.models.MedicoModel;
import med.vol.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
