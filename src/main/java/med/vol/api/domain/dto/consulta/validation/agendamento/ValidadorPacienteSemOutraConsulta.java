package med.vol.api.domain.dto.consulta.validation.agendamento;

import med.vol.api.domain.dto.consulta.DadosAgendamentoConsulta;
import med.vol.api.domain.repository.ConsultaRepository;
import med.vol.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsulta implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if(pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente já possui ma consulta agendada nesse dia");
        }

    }
}
