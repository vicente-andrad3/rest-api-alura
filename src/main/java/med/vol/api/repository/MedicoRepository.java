package med.vol.api.repository;

import med.vol.api.models.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {

}
