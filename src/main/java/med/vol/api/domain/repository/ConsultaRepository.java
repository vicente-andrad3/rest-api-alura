package med.vol.api.domain.repository;

import med.vol.api.domain.models.ConsultaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {
}
