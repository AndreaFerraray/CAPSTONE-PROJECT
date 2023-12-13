package CAPSTONE.PROJECT.controller;

import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.entities.Prenotazione;
import CAPSTONE.PROJECT.entities.User;
import CAPSTONE.PROJECT.exceptions.BadRequestException;
import CAPSTONE.PROJECT.exceptions.NoMoreAvailableSpotsException;
import CAPSTONE.PROJECT.payload.NewPrenotazioneDTO;
import CAPSTONE.PROJECT.service.PrenotazioniService;
import CAPSTONE.PROJECT.service.UserService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

@Autowired
    PrenotazioniService prenotazioniService;
@Autowired
    UserService userService;

@GetMapping("/me")
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public List<Prenotazione> getUserPrenotazioni(@AuthenticationPrincipal UserDetails userDetails){
   return prenotazioniService.getUserPrenotazione(userDetails);

}
    @PostMapping("/addBooking/me")
    public User addBooking(@AuthenticationPrincipal UserDetails userDetails, @RequestBody NewPrenotazioneDTO newPrenotazioneDTO, Campeggio campeggio) throws NoMoreAvailableSpotsException {
        /*User user = userService.findUserByUsername(userDetails.getUsername());
        Long userId = user.getId();
        if (prenotazioniService.isUserAlreadyBooked(userId, newPrenotazioneDTO.data_check_in(), newPrenotazioneDTO.data_check_out(), campeggio.getId())) {
            throw new BadRequestException("L'utente ha già prenotato per le date selezionate");
        }*/

        if (newPrenotazioneDTO.data_check_in() == null || newPrenotazioneDTO.data_check_out() == null) {
            throw new BadRequestException("Le date di check-in e check-out devono essere specificate");
        }

        if (newPrenotazioneDTO.data_check_in().after(newPrenotazioneDTO.data_check_out())) {
            throw new BadRequestException("La data di check-in non può essere successiva a quella di check-out");
        }

        if (userDetails != null) {
            User user = userService.findUserByUsername(userDetails.getUsername());

            return  prenotazioniService.addBooking(user,newPrenotazioneDTO);

        } else {
            throw new BadRequestException("utente non trovato");
        }
    }


 /*   @DeleteMapping("/deleteOneBooking/me")
    public User deleteOneBooking(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Long campeggioId){
        if (userDetails != null) {
            User user = userService.findUserByUsername(userDetails.getUsername());
            return   userService.deleteOneBooking(user, campeggioId);

        } else {
            throw  new BadRequestException("utente non trovato");
        }
    }*/
}
