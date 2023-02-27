package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.domain.dto.consulta.DadosAgendamentoConsulta;
import med.vol.api.domain.dto.consulta.DadosDetalhamentoConsulta;
import med.vol.api.infra.service.AgendaDeConsultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultasService agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados); // Regra de negócio
        return ResponseEntity.ok(dto);
    }
}
