package med.vol.api.domain.dto.consulta;

import med.vol.api.domain.models.ConsultaModel;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public DadosDetalhamentoConsulta(ConsultaModel consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
