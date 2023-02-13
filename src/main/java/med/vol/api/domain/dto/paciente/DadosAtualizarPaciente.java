package med.vol.api.domain.dto.paciente;

import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.dto.data.DadosEndereco;

public record DadosAtualizarPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
