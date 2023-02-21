package med.vol.api.domain.repository;

import med.vol.api.domain.models.PacienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {
    Page<PacienteModel> findAllByAtivoTrue(Pageable paginacao);
    @Query("""
            select p.ativo
            from Paciente p
            where
            p.id = :id
            """)
    boolean findAtivoById(Long idPaciente);

    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
