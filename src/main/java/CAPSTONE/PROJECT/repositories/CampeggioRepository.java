package CAPSTONE.PROJECT.repositories;

import CAPSTONE.PROJECT.entities.Campeggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CampeggioRepository extends JpaRepository <Campeggio, Long> {
    Optional<Campeggio> findByEmail(String email);

    Optional<Campeggio> findByIndirizzo(String indirizzo);
}
