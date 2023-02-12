package med.vol.api.dto.medico;

import med.vol.api.dto.data.Endereco;
import med.vol.api.dto.data.Especialidade;
import med.vol.api.models.MedicoModel;

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
