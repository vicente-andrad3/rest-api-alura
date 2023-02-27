package med.vol.api.domain.dto.consulta.validation.agendamento;

import med.vol.api.domain.dto.consulta.DadosAgendamentoConsulta;
import med.vol.api.domain.repository.PacienteRepository;
import med.vol.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteIsAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteIsAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada por paciente inativo!");
        }

    }

}
