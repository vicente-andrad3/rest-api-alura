package med.vol.api.domain.dto.consulta.validation;

import med.vol.api.domain.dto.consulta.DadosAgendamentoConsulta;
import med.vol.api.domain.repository.MedicoRepository;
import med.vol.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta  {
    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        // Escolha do médico opcional
        if(dados.idMedico() == null) {
            return;
        }

        var medicoIsAtivo = repository.findAtivoById(dados.idMedico());
        if(!medicoIsAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo!");
        }

    }

}
