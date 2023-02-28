package med.vol.api.controller;

import med.vol.api.domain.dto.consulta.DadosAgendamentoConsulta;
import med.vol.api.domain.dto.consulta.DadosDetalhamentoConsulta;
import med.vol.api.domain.dto.data.Especialidade;
import med.vol.api.infra.service.AgendaDeConsultasService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest // Subir contexto de teste no spring
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc; // Simular requisições http

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson; // Recebe dados do agendamento

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson; // Porém devolve dados do detalhamento

    @MockBean
    private AgendaDeConsultasService agendaDeConsultas;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão inválidas")
    @WithMockUser // Mock de um user
    void agendarConsulta01() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse(); // Dispara um requisição para o controller

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value()); // Verificar se o status é 400
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão válidas")
    @WithMockUser // Mock de um user
    void agendarConsulta02() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;
        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l, 5l, data);

        when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento); // Mockito

        var response = mvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaJson.write(new DadosAgendamentoConsulta(2l, 5l, data, especialidade))
                                        .getJson()) // Tranformaem json
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJson.write(dadosDetalhamento)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado); // Conteúdo como string e verificar se ele é igual a json esperado
    }
}