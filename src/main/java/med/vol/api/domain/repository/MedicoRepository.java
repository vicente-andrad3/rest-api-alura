package med.vol.api.domain.repository;

import med.vol.api.domain.dto.data.Especialidade;
import med.vol.api.domain.models.MedicoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {
    Page<MedicoModel> findAllByAtivoTrue(Pageable paginacao); // Faz a QUERY com base na nomeclatura da função

    // Lógica SQL
    @Query("""
            select m from Medico m
            where
            m.ativo = 1
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = :data
            )
            order bu rand()
            limit 1
            """)
    MedicoModel escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id
            """)
    boolean findAtivoById(Long idMedico);

    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);
}
