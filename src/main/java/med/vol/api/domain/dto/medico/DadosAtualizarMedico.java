package med.vol.api.domain.dto.medico;

import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.dto.data.DadosEndereco;

public record DadosAtualizarMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
