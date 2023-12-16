package CAPSTONE.PROJECT.controller;

import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.entities.Prenotazione;
import CAPSTONE.PROJECT.entities.User;
import CAPSTONE.PROJECT.exceptions.BadRequestException;
import CAPSTONE.PROJECT.exceptions.NoMoreAvailableSpotsException;
import CAPSTONE.PROJECT.exceptions.UnauthorizedException;
import CAPSTONE.PROJECT.payload.NewPrenotazioneDTO;
import CAPSTONE.PROJECT.service.PrenotazioniService;
import CAPSTONE.PROJECT.service.UserService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


        if (newPrenotazioneDTO.data_check_in() == null || newPrenotazioneDTO.data_check_out() == null) {
            throw new BadRequestException("Le date di check-in e check-out devono essere specificate");
        }

        if ((newPrenotazioneDTO.data_check_in()).isAfter(newPrenotazioneDTO.data_check_out()) ){
            throw new BadRequestException("La data di check-in non può essere successiva a quella di check-out");
        }
        if (userDetails != null) {
            User user = userService.findUserByUsername(userDetails.getUsername());

            return  prenotazioniService.addBooking(user,newPrenotazioneDTO);

        } else {
            throw new BadRequestException("utente non trovato");
        }
    }





    @DeleteMapping("/deleteOneBooking/me/{prenotazioneId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<User> deleteOneBooking(@AuthenticationPrincipal UserDetails userDetails, @PathVariable long prenotazioneId) {
        if (userDetails != null) {
            User user = userService.findUserByUsername(userDetails.getUsername());
            Prenotazione prenotazione = prenotazioniService.findPrenotazioneById(prenotazioneId);

            if (prenotazione != null && prenotazione.getUser().equals(user)) {
                prenotazioniService.findByIdAndDelete(prenotazioneId);
                User updatedUser = userService.findUserByUsername(userDetails.getUsername());

                return ResponseEntity.ok(updatedUser);
            } else {
                throw new UnauthorizedException("Non sei autorizzato a eliminare questa prenotazione");
            }
        } else {
            throw new BadRequestException("Utente non trovato");
        }
    }

}

