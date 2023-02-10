package med.vol.api.data;

import med.vol.api.models.MedicoModel;

public record DadosListagemMedico(
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public DadosListagemMedico(MedicoModel medico){
        this(medico.getName(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
