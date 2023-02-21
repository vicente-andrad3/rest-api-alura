package med.vol.api.infra.service;

import med.vol.api.domain.dto.consulta.DadosAgendamentoConsulta;
import med.vol.api.domain.dto.consulta.validation.ValidadorAgendamentoDeConsulta;
import med.vol.api.domain.models.ConsultaModel;
import med.vol.api.domain.models.MedicoModel;
import med.vol.api.domain.repository.ConsultaRepository;
import med.vol.api.domain.repository.MedicoRepository;
import med.vol.api.domain.repository.PacienteRepository;
import med.vol.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Regra de negócios
public class AgendaDeConsultasService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    // Estamos iniciando e utilizando o SOLID
    // S -> Single Reponsability Principle
    // O -> Open-Closed Principle: Fechada para modificação, porém aberta para modificações
    // D -> Dependency Inversion Principle: A classe em si depende somente de uma abstração, e não do conjunto de classes de validação
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores; // Spring boot cria uma lista com todos os validadores criados por meio da interface e os injeta

    public void agendar(DadosAgendamentoConsulta dados) {
        if(!pacienteRepository.existsById(dados.idPaciente())){ // regra de negócio paciente - obrigatório
            throw new ValidacaoException("Id do paciente informado não existe!");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){ // Regra de negócio médico - opcional
            throw new ValidacaoException("Id do médico informado não existe!");
        }
        // Validações
        validadores.forEach(v -> v.validar(dados)); // Validadores validados
        
        // Um erro apareceu aqui
        // Resolução: Implementar método findByID, onde o resultado pode ou não ser Optional!
        // Essa implementação é feita dentro das classe repository de cada entity
        // Método get() pega o objeto de fato para validar

        // Melhor resolução: Utilizamos do getReferenceById() para receber um id da entidy paciente/medico

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new ConsultaModel(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
    }

    private MedicoModel escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatório quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

}
