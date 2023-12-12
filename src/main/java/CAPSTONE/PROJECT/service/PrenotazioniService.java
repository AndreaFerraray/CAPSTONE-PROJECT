package CAPSTONE.PROJECT.service;

import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.entities.Prenotazione;
import CAPSTONE.PROJECT.entities.User;
import CAPSTONE.PROJECT.payload.NewPrenotazioneDTO;
import CAPSTONE.PROJECT.repositories.CampeggioRepository;
import CAPSTONE.PROJECT.repositories.PrenotazioneRepository;
import CAPSTONE.PROJECT.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioniService {
@Autowired
    UserRepository userRepository;
@Autowired
    CampeggioRepository campeggioRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public User addBooking(User user, NewPrenotazioneDTO newPrenotazioneDTO) {
        User existingUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        Campeggio campeggio = campeggioRepository.findById(newPrenotazioneDTO.campeggioId())
                .orElseThrow(() -> new EntityNotFoundException("Campeggio non trovato"));


        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUser(user);
        prenotazione.setCampeggio(campeggio);
        prenotazione.setDataPrenotazione(newPrenotazioneDTO.dataPrenotazione());
        prenotazione.setData_check_in(newPrenotazioneDTO.data_check_in());
        prenotazione.setData_check_out(newPrenotazioneDTO.data_check_out());
        prenotazione.setOspiti(newPrenotazioneDTO.ospiti());
        prenotazione.setNotti(newPrenotazioneDTO.notti());

        System.out.println("aaaaaaaaa");
        prenotazioneRepository.save(prenotazione);
        return existingUser.addBooking(prenotazione);
    }


/*    public User deleteOneBooking(User user, Long campeggioId) {
        User existingUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        Campeggio campeggio = campeggioRepository.findById(campeggioId)
                .orElseThrow(() -> new EntityNotFoundException("Campeggio non trovato"));

        existingUser.deleteOneBooking(campeggio);
        return userRepository.save(existingUser);
    }*/
}
