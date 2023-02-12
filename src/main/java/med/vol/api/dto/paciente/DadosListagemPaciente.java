package med.vol.api.dto.paciente;

import med.vol.api.models.PacienteModel;

public record DadosListagemPaciente(
        String nome,
        String email,
        String cpf
) {
    public DadosListagemPaciente(PacienteModel paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}