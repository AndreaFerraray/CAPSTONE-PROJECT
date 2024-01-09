package CAPSTONE.PROJECT.controller;

import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.exceptions.BadRequestException;
import CAPSTONE.PROJECT.exceptions.NotFoundException;
import CAPSTONE.PROJECT.payload.NewCampeggioDTO;
import CAPSTONE.PROJECT.repositories.CampeggioRepository;
import CAPSTONE.PROJECT.service.CampeggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/campeggi")
public class CampeggioController {
@Autowired
CampeggioService campeggioService;
    @Autowired
    private CampeggioRepository campeggioRepository;


    @GetMapping("")
    Page <Campeggio> getCampeggi(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String orderBy){
        return campeggioService.getCampeggi(page,size, orderBy);
    }
/*    @GetMapping("filter")
    Page <Campeggio> getCampeggiFiltrati( @RequestParam String luogo,  @RequestParam boolean caniAmmessi, @RequestParam boolean piscina, @RequestParam boolean animazione, @RequestParam boolean market, @RequestParam boolean ristorante, @RequestParam long stelle  @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String orderBy){
        return campeggioService
    }*/

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Campeggio saveCampeggio(@Validated @RequestBody NewCampeggioDTO newCampeggioDTO ) throws IOException {
        return campeggioService.saveCampeggio(newCampeggioDTO);}

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    Optional<Campeggio> getCampeggio(@PathVariable long id) {
        return campeggioService.findCampeggioById(id);
    }

/*    @GetMapping("/cerca/{indirizzo}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<?> getCampeggioByIndirizzo(@PathVariable String indirizzo) throws IOException {
        List<Campeggio> campeggi = campeggioRepository.findByIndirizzoContaining(indirizzo);

        if (campeggi.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nessun campeggio trovato");
        } else {

            return ResponseEntity.ok(campeggi);
        }
    }*/
    @GetMapping("/cerca/")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<?> findByIndirizzoAndFilters(@RequestParam(required = false) String indirizzo,
                                                     @RequestParam(required = false) Boolean wifi,
                                                     @RequestParam(required = false) Boolean animaliAmmessi,
                                                     @RequestParam(required = false) Boolean piscina,
                                                     @RequestParam(required = false) Boolean animazione,
                                                     @RequestParam(required = false) Boolean market,
                                                     @RequestParam(required = false) Boolean ristorante) throws IOException {

        if (!wifi && !animaliAmmessi && !piscina && !animazione && !market && !ristorante) {
            List<Campeggio> campeggiByIndirizzo = campeggioRepository.findByIndirizzoContaining(indirizzo);

            if (campeggiByIndirizzo.isEmpty()) {
                System.out.println("Nessun campeggio trovato");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nessun campeggio trovato");
            } else {
                System.out.println("Campeggi trovati con indirizzo: " + campeggiByIndirizzo.size());
                return ResponseEntity.ok(campeggiByIndirizzo);
            }
        } else {
            System.out.println(wifi);
            List<Campeggio> campeggi = campeggioRepository.findByFilters(indirizzo, wifi, animaliAmmessi, piscina, animazione, market,ristorante);
            if (campeggi.isEmpty()) {
                System.out.println("Nessun campeggio trovato");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nessun campeggio trovato");
            } else {
                System.out.println("Campeggi trovati con filtri: " + campeggi.size());
                return ResponseEntity.ok(campeggi);
            }
        }
    }
@DeleteMapping("/delete/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletecamping(@PathVariable long id){
         campeggioService.deleteCampeggio(id);
}
}

