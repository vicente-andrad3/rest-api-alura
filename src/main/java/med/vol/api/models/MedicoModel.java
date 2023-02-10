package med.vol.api.models;

import jakarta.persistence.*;
import lombok.*;

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

    public MedicoModel(DadosCadastroMedico dados) {
        this.name = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }
}
