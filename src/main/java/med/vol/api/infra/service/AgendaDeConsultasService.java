package med.vol.api.infra.service;

import med.vol.api.domain.dto.consulta.DadosAgendamentoConsulta;
import med.vol.api.domain.models.ConsultaModel;
import med.vol.api.domain.models.MedicoModel;
import med.vol.api.domain.repository.ConsultaRepository;
import med.vol.api.domain.repository.MedicoRepository;
import med.vol.api.domain.repository.PacienteRepository;
import med.vol.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Regra de negócios
public class AgendaDeConsultasService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;
    public void agendar(DadosAgendamentoConsulta dados) {
        if(!pacienteRepository.existsById(dados.idPaciente())){ // regra de negócio paciente - obrigatório
            throw new ValidacaoException("Id do paciente informado não existe!");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){ // Regra de negócio médico - opcional
            throw new ValidacaoException("Id do médico informado não existe!");
        }


        // Um erro apareceu aqui
        // Resolução: Implementar método findByID, onde o resultado pode ou não ser Optional!
        // Essa implementação é feita dentro das classe repository de cada entity
        // Método get() pega o objeto de fato para validar

        var paciente = pacienteRepository.findAllById(dados.idPaciente()).get();
        var medico = escolherMedico(dados);
        var consulta = new ConsultaModel(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
    }

    private MedicoModel escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        return null;
    }

}
