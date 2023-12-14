package CAPSTONE.PROJECT.controller;

import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.entities.TipoSistemazione;
import CAPSTONE.PROJECT.payload.NewSistemazioneDTO;
import CAPSTONE.PROJECT.service.TipoSistemazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sistemazioni")
public class SistemazioneController {
    @Autowired
    TipoSistemazioneService tipoSistemazioneService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public List<TipoSistemazione> getSistemazioni(@RequestBody Long campeggio_id){
        return tipoSistemazioneService.getTipoSistemazioneByCampeggio(campeggio_id);
    }
@PostMapping("")
@ResponseStatus(HttpStatus.CREATED)
@PreAuthorize("hasAuthority('ADMIN')")
    public TipoSistemazione addSistemazione(@RequestBody NewSistemazioneDTO newSistemazioneDTO){
        return tipoSistemazioneService.saveSistemazione(newSistemazioneDTO);
}

@DeleteMapping("/delete")
@ResponseStatus(HttpStatus.NO_CONTENT)
@PreAuthorize("hasAuthority('ADMIN')")
    public void deleteSistemazioneByCampeggio(@RequestBody Long id){
        tipoSistemazioneService.findByIdAndDelete(id);
}


}
