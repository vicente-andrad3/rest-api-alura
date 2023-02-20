package med.vol.api.domain.repository;

import med.vol.api.domain.models.PacienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {

    Page<PacienteModel> findAllByAtivoTrue(Pageable paginacao);

    Optional<PacienteModel> findAllById(Long idPaciente);
}
