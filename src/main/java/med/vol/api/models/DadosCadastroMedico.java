package med.vol.api.models;

import med.vol.api.data.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) { // Classes Record já possuem todos gets and sets
}
