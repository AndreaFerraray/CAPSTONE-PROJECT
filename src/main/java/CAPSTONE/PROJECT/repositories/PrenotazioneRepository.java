package CAPSTONE.PROJECT.repositories;

import CAPSTONE.PROJECT.entities.Prenotazione;
import CAPSTONE.PROJECT.entities.User;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByUser(User user);



   


/*@Query("SELECT p FROM Prenotazione p WHERE p.user= :ID_USER AND p.campeggio_id = :ID_CAMPEGGIO AND p.data_check_in <=NUOVA_DATA_INIZIO  AND p.data_check_out >= NUOVA_DATA_FINE")
    List <Prenotazione>findByUserAndDateRange(@Param("ID_USER") Long id_user, @Param("NUOVA_DATA_INIZIO") Date data_check_in, @Param("NUOVA_DATA_FINE") Date data_check_out, @Param("ID_CAMPEGGIO") Long campeggio_id);*/

}
