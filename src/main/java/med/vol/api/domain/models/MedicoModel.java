package med.vol.api.domain.models;

import jakarta.persistence.*;
import lombok.*;
import med.vol.api.domain.dto.data.Endereco;
import med.vol.api.domain.dto.data.Especialidade;
import med.vol.api.domain.dto.medico.DadosAtualizarMedico;
import med.vol.api.domain.dto.medico.DadosCadastroMedico;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor // Construtor vazio
@AllArgsConstructor // Construtor com todos os campos
@EqualsAndHashCode(of = "id") // hashcode de acordo com o id
public class MedicoModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded // Indica que a classe vai ficar separada, porém estará contida dentro da classe MedicoModel
    private Endereco endereco;

    private boolean ativo;

    public MedicoModel(DadosCadastroMedico dados) {
        this.ativo = true;
        this.name = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizarMedico dados) {
        if(dados.nome() != null){
            this.name = dados.nome();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluirMedico() {
        this.ativo = false;
    }
}
