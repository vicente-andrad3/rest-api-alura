package med.vol.api.domain.dto.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.dto.data.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future // Data pro futuro
        LocalDateTime data,

        Especialidade especialidade
) {
}
