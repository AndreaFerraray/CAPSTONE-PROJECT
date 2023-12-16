package CAPSTONE.PROJECT.repositories;

import CAPSTONE.PROJECT.entities.Campeggio;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CampeggioRepository extends JpaRepository <Campeggio, Long> {
    Optional<Campeggio> findByEmail(String email);

    Optional<Campeggio> findByIndirizzo(String indirizzo);

    @Query("SELECT c FROM Campeggio c WHERE LOWER(c.indirizzo) LIKE LOWER(concat('%', :indirizzo, '%'))")
    List<Campeggio> findByIndirizzoContaining(@Param("indirizzo") String indirizzo);
    Campeggio findCampeggioById(long id);
}
