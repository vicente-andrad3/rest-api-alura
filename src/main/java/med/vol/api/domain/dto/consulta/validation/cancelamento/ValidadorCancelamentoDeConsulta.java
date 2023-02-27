package med.vol.api.domain.dto.consulta.validation.cancelamento;

import med.vol.api.domain.dto.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);

}