package CAPSTONE.PROJECT.repositories;

import CAPSTONE.PROJECT.entities.TipoSistemazione;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TipoSistemazioneRepository extends JpaRepository <TipoSistemazione, Long> {
    @Query("SELECT ts FROM TipoSistemazione ts WHERE ts.campeggioSistemazione.id  = :campeggio_id")
    List<TipoSistemazione> getAllByIdCampeggio(@Param("campeggioId") Long campeggio_id);


    Optional<Object> findByNomeSistemazione(String nomeSistemazione);
}
