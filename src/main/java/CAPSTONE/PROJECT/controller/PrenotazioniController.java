package CAPSTONE.PROJECT.controller;

import CAPSTONE.PROJECT.entities.User;
import CAPSTONE.PROJECT.exceptions.BadRequestException;
import CAPSTONE.PROJECT.payload.NewPrenotazioneDTO;
import CAPSTONE.PROJECT.service.PrenotazioniService;
import CAPSTONE.PROJECT.service.UserService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

@Autowired
    PrenotazioniService prenotazioniService;
@Autowired
    UserService userService;


    @PostMapping("/addBooking/me")
    public User addBooking(@AuthenticationPrincipal UserDetails userDetails, @RequestBody NewPrenotazioneDTO newPrenotazioneDTO){
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
