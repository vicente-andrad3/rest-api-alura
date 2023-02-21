package med.vol.api.domain.dto.consulta.validation;

import med.vol.api.domain.dto.consulta.DadosAgendamentoConsulta;
import med.vol.api.infra.exception.ValidacaoException;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY); // Checagem se é domingo
        var beforeOpenClinic = dataConsulta.getHour() < 7; // Checagem de horário
        var afterClosedClinic = dataConsulta.getHour() > 18;
        if(domingo || beforeOpenClinic || afterClosedClinic) {
            throw  new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
