package CAPSTONE.PROJECT.service;

import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.entities.Prenotazione;
import CAPSTONE.PROJECT.entities.TipoSistemazione;
import CAPSTONE.PROJECT.entities.User;
import CAPSTONE.PROJECT.exceptions.NoMoreAvailableSpotsException;
import CAPSTONE.PROJECT.exceptions.NotFoundException;
import CAPSTONE.PROJECT.payload.NewPrenotazioneDTO;
import CAPSTONE.PROJECT.repositories.CampeggioRepository;
import CAPSTONE.PROJECT.repositories.PrenotazioneRepository;
import CAPSTONE.PROJECT.repositories.TipoSistemazioneRepository;
import CAPSTONE.PROJECT.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioniService {
    @Autowired
    UserService userService;
    @Autowired
    CampeggioService campeggioService;
@Autowired
    UserRepository userRepository;
@Autowired
    CampeggioRepository campeggioRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private TipoSistemazioneRepository tipoSistemazioneRepository;


    public User addBooking(User user, NewPrenotazioneDTO newPrenotazioneDTO) throws NoMoreAvailableSpotsException {
        User existingUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        Campeggio campeggio = campeggioRepository.findById(newPrenotazioneDTO.campeggioId())
                .orElseThrow(() -> new EntityNotFoundException("Campeggio non trovato"));

       TipoSistemazione tipoSistemazione=tipoSistemazioneRepository.findById(newPrenotazioneDTO.tipoSistemazioneId())
               .orElseThrow(() -> new EntityNotFoundException("sistemazione non trovata"));;

        long numeroNotti = ChronoUnit.DAYS.between(newPrenotazioneDTO.data_check_in(),newPrenotazioneDTO.data_check_out());

        long tariffaGiornaliera = tipoSistemazione.getPrezzoNotte();

        long costoTotale= numeroNotti * tariffaGiornaliera;
        System.out.println(costoTotale);

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUser(user);
        prenotazione.setCampeggio(campeggio);
        prenotazione.setDataPrenotazione(newPrenotazioneDTO.dataPrenotazione());
        prenotazione.setData_check_in(newPrenotazioneDTO.data_check_in());
        prenotazione.setData_check_out(newPrenotazioneDTO.data_check_out());
        prenotazione.setOspiti(newPrenotazioneDTO.ospiti());
         prenotazione.setCani(newPrenotazioneDTO.cani());
         prenotazione.setTipoSistemazione(tipoSistemazione);
         prenotazione.setCostoTotale(costoTotale);

        System.out.println((newPrenotazioneDTO.tipoSistemazioneId()));

        System.out.println("aaaaaaaaa");
        prenotazioneRepository.save(prenotazione);

        return existingUser.addBooking(prenotazione);
    }

    public List<Prenotazione> getUserPrenotazione(UserDetails userDetails){
        User user = userService.findUserByUsername(userDetails.getUsername());
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByUser(user);
        if (!prenotazioni.isEmpty()) {
            return prenotazioni;
        } else {
            throw new NotFoundException("nessuna prenotazione trovata");

        }
    }



    public void findByIdAndDelete(long prenotazioneId) {

Prenotazione prenotazione= this.findPrenotazioneById(prenotazioneId);
prenotazioneRepository.delete(prenotazione);

    }



    public Prenotazione findPrenotazioneById(long id) {
        return  prenotazioneRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }



}
