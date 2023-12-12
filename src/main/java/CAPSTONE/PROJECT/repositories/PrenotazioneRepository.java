package CAPSTONE.PROJECT.repositories;

import CAPSTONE.PROJECT.entities.Prenotazione;
import CAPSTONE.PROJECT.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {



}
