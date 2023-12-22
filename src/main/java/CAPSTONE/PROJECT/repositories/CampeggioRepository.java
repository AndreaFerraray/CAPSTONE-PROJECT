package CAPSTONE.PROJECT.repositories;

import CAPSTONE.PROJECT.entities.Campeggio;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Repository
public interface CampeggioRepository extends JpaRepository <Campeggio, Long> {
    Optional<Campeggio> findByEmail(String email);

    Optional<Campeggio> findByIndirizzo(String indirizzo);

    @Query("SELECT c FROM Campeggio c WHERE LOWER(c.indirizzo) LIKE LOWER(concat('%', :indirizzo, '%'))")
    List<Campeggio> findByIndirizzoContaining(@Param("indirizzo") String indirizzo);
    Campeggio findCampeggioById(long id);
    @Query("SELECT c FROM Campeggio c WHERE" +
            " (LOWER(c.indirizzo) LIKE LOWER(concat('%', :indirizzo, '%')) OR :indirizzo IS NULL OR :indirizzo = '')" +
            " AND (:wifi IS NULL OR c.wifi = :wifi)" +
            " AND (:animaliAmmessi IS NULL OR c.caniAmmessi = :animaliAmmessi)" +
            " AND (:piscina IS NULL OR c.piscina = :piscina)" +
            " AND (:animazione IS NULL OR c.animazione = :animazione)" +
            " AND (:market IS NULL OR c.market = :market)"+
            " AND (:ristorante IS NULL OR c.ristorante = :ristorante)")
    List<Campeggio> findByFilters(
            @Param("indirizzo") String indirizzo,
            @Param("wifi") Boolean wifi,
            @Param("animaliAmmessi") Boolean animaliAmmessi,
            @Param("piscina") Boolean piscina,
            @Param("animazione") Boolean animazione,
            @Param("market") Boolean market,
            @Param("ristorante") Boolean ristorante);
}

