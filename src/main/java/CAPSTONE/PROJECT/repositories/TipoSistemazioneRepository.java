package CAPSTONE.PROJECT.repositories;

import CAPSTONE.PROJECT.entities.TipoSistemazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TipoSistemazioneRepository extends JpaRepository <TipoSistemazione, Long> {
    List<TipoSistemazione> getAllById(Long campeggioId);

    Optional<Object> findByNomeSistemazione(String nomeSistemazione);
}
