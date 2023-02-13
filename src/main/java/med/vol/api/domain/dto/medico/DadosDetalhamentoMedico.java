package med.vol.api.domain.dto.medico;

import med.vol.api.domain.dto.data.Endereco;
import med.vol.api.domain.dto.data.Especialidade;
import med.vol.api.domain.models.MedicoModel;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public DadosDetalhamentoMedico(MedicoModel medico) {
        this(medico.getId(), medico.getName(), medico.getEmail(), medico.getEmail(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
