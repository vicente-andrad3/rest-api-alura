package med.vol.api.domain.dto.paciente;

import med.vol.api.domain.models.PacienteModel;

public record DadosListagemPaciente(
        String nome,
        String email,
        String cpf
) {
    public DadosListagemPaciente(PacienteModel paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
