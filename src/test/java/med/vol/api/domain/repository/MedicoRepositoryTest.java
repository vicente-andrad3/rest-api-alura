package med.vol.api.domain.repository;

import med.vol.api.domain.dto.data.DadosEndereco;
import med.vol.api.domain.dto.data.Especialidade;
import med.vol.api.domain.dto.medico.DadosCadastroMedico;
import med.vol.api.domain.dto.paciente.DadosCadastroPaciente;
import med.vol.api.domain.models.ConsultaModel;
import med.vol.api.domain.models.MedicoModel;
import med.vol.api.domain.models.PacienteModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // Anotação utilizada para testar uma interface Repository
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Configuração de teste sem substituir as configuração do banco in-memory pelas do banco de dados puro
@ActiveProfiles("test") // Ler o proprieties .test
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null, quando unico medico cadastrado não está disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        // Given ou arrange -- Cadastro de informações
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY)) // Roda sempre na próxima segunda-feira
                .atTime(10, 0); // Horário sempre as 10 horas


        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@gmail.com", "09686930402");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        // when ou act -- Executo ação de teste
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        // then ou assert -- Verificar se o resultado é o esperado
        assertThat(medicoLivre).isNull();

    }

    @Test
    @DisplayName("Deveria devolver medico, quando ele estiver disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY)) // Roda sempre na próxima segunda-feira
                .atTime(10, 0); // Horário sempre as 10 horas

        var medico= cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isEqualTo(medico);

    }

    private void cadastrarConsulta(MedicoModel medico, PacienteModel paciente, LocalDateTime data) {
        em.persist(new ConsultaModel(null, medico, paciente, data, null));
    }

    private MedicoModel cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new MedicoModel(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private PacienteModel cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new PacienteModel(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}