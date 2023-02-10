package med.vol.api.dto.medico;

import jakarta.validation.constraints.NotNull;
import med.vol.api.dto.data.DadosEndereco;

public record DadosAtualizarMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
