package med.vol.api.domain.repository;

import med.vol.api.domain.models.MedicoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {
    Page<MedicoModel> findAllByAtivoTrue(Pageable paginacao); // Faz a QUERY com base na nomeclatura da função

    Optional<MedicoModel> findAllById(Long idMedico);
}
