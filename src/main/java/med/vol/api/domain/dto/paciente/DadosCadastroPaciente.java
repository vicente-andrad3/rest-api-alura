package med.vol.api.domain.dto.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.dto.data.DadosEndereco;

public record DadosCadastroPaciente(
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank(message = "{email.obrigatorio}")
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank(message = "{telefone.obrigatorio}")
        String telefone,
        @NotBlank(message = "{cpf.obrigatorio}")
        String cpf,
        @NotNull(message = "{endereco.obrigatorio}")
        @Valid
        DadosEndereco endereco
) {
}
