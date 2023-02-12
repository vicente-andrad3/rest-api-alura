package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.dto.paciente.DadosAtualizarPaciente;
import med.vol.api.dto.paciente.DadosCadastroPaciente;
import med.vol.api.dto.paciente.DadosListagemPaciente;
import med.vol.api.models.PacienteModel;
import med.vol.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dados){
        repository.save(new PacienteModel(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listarPaciente(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizarPaciente(@RequestBody @Valid DadosAtualizarPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluirPaciente();
    }

}
