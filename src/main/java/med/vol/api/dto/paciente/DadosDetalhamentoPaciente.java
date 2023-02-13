package med.vol.api.dto.paciente;

import med.vol.api.dto.data.Endereco;
import med.vol.api.models.PacienteModel;

public record DadosDetalhamentoPaciente(
        Long id,
        String name,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {
    public DadosDetalhamentoPaciente(PacienteModel paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
