package med.vol.api.domain.dto.consulta.validation;

import med.vol.api.domain.dto.consulta.DadosAgendamentoConsulta;
import med.vol.api.infra.exception.ValidacaoException;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioAntecendencia {

    public void validar(DadosAgendamentoConsulta dados){
        var dadosConsulta = dados.data();
        var nowTime = LocalDateTime.now(); // Pega o horário atual
        var minutedDifference = Duration.between(nowTime, dadosConsulta).toMinutes(); // verifica a diferença de minutos entre o tempo atual e a data de agendamento

        if(minutedDifference < 30) {
            throw new ValidacaoException("COnsulta deve ser agendada com antecedência mínima de 30 minutos!");
        }

    }
}
