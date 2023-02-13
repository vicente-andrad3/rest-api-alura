package med.vol.api.domain.dto.medico;

import med.vol.api.domain.dto.data.Especialidade;
import med.vol.api.domain.models.MedicoModel;

public record DadosListagemMedico(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public DadosListagemMedico(MedicoModel medico){
        this(medico.getId(), medico.getName(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
