package CAPSTONE.PROJECT.service;


import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.exceptions.BadRequestException;
import CAPSTONE.PROJECT.exceptions.ErrorsPayload;
import CAPSTONE.PROJECT.exceptions.NoMoreAvailableSpotsException;
import CAPSTONE.PROJECT.exceptions.NotFoundException;
import CAPSTONE.PROJECT.payload.NewCampeggioDTO;
import CAPSTONE.PROJECT.repositories.CampeggioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class CampeggioService {

    @Autowired
    CampeggioRepository campeggioRepository;
    @Autowired
    PasswordEncoder bcrypt;

    public Page<Campeggio> getCampeggi (int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return campeggioRepository.findAll(pageable);
    }



    public Campeggio saveCampeggio(NewCampeggioDTO newCampeggioDTO) throws IOException {

        campeggioRepository.findByEmail(newCampeggioDTO.email()).ifPresent(campeggio ->{
            throw new BadRequestException("La mail "+ campeggio.getEmail() + " è già presente! ");
        });

        campeggioRepository.findByIndirizzo(newCampeggioDTO.indirizzo()).ifPresent(campeggio ->{
            throw new BadRequestException("Id "+ campeggio.getIndirizzo() + " è già presente! ");
        });
        Campeggio campeggio= new Campeggio();
        campeggio.setIndirizzo(newCampeggioDTO.indirizzo());
        campeggio.setPostiDisp(newCampeggioDTO.postiDisp());
        campeggio.setEmail(newCampeggioDTO.email());
        campeggio.setNumeroTelefono(newCampeggioDTO.numeroTelefono());
        campeggio.setNome(newCampeggioDTO.nome());
        campeggio.setCaniAmmessi(newCampeggioDTO.caniAmmessi());
        campeggio.setPiscina(newCampeggioDTO.piscina());
        campeggio.setAnimazione(newCampeggioDTO.animazione());
        campeggio.setMarket(newCampeggioDTO.market());
        campeggio.setRistorante(newCampeggioDTO.ristorante());
        campeggio.setStelle(newCampeggioDTO.stelle());
        campeggio.setImmagini(newCampeggioDTO.immagini());
        campeggio.setPassword(bcrypt.encode(newCampeggioDTO.password()));
        return campeggioRepository.save(campeggio);
    }


    public Optional<Campeggio> findCampeggioById(long id) throws NotFoundException {
        return Optional.ofNullable(campeggioRepository.findCampeggioById(id));
    }

  /*  public void decrementaPostiDisponibili(Long campeggioId, Long ospiti) throws NoMoreAvailableSpotsException {
        Campeggio campeggio = campeggioRepository.findById(campeggioId)
                .orElseThrow(() -> new EntityNotFoundException("Campeggio non trovato"));


        if (campeggio.getPostiDisp() > 0) {
            if(ospiti != null){
            campeggio.setPostiDisp(campeggio.getPostiDisp() - ospiti);
            campeggioRepository.save(campeggio);}
            else{ throw new RuntimeException("ospiti null");
            }
        } else {
            throw new NoMoreAvailableSpotsException("Nessun posto disponibile nel campeggio");
        }
    }
*/
}
