package med.vol.api.domain.dto.consulta.validation;

import med.vol.api.domain.dto.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
