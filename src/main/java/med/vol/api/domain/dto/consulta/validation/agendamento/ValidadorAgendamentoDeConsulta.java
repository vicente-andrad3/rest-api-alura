package med.vol.api.domain.dto.consulta.validation.agendamento;

import med.vol.api.domain.dto.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
