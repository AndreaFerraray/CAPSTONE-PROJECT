package CAPSTONE.PROJECT.service;

import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.entities.TipoSistemazione;
import CAPSTONE.PROJECT.exceptions.BadRequestException;
import CAPSTONE.PROJECT.exceptions.NotFoundException;
import CAPSTONE.PROJECT.payload.NewSistemazioneDTO;
import CAPSTONE.PROJECT.repositories.CampeggioRepository;
import CAPSTONE.PROJECT.repositories.TipoSistemazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TipoSistemazioneService {
    @Autowired
    TipoSistemazioneRepository tipoSistemazioneRepository;
    @Autowired
    private CampeggioRepository campeggioRepository;

    public List<TipoSistemazione> getTipoSistemazioneByCampeggio(Long campeggio_id) {
        return tipoSistemazioneRepository.getAllByIdCampeggio(campeggio_id);
    }


    public TipoSistemazione saveSistemazione(NewSistemazioneDTO newSistemazioneDTO) {
        Campeggio campeggio = campeggioRepository.findCampeggioById(newSistemazioneDTO.campeggio_id());

        if (campeggio != null) {


            TipoSistemazione tipoSistemazione = new TipoSistemazione();
            tipoSistemazione.setCampeggioSistemazione(campeggio);
            tipoSistemazione.setNomeSistemazione(newSistemazioneDTO.nomeSistemazione());
            tipoSistemazione.setPersoneAmmesse(newSistemazioneDTO.personeAmmesse());
            tipoSistemazione.setPrezzoNotte(newSistemazioneDTO.prezzoNotte());
            tipoSistemazione.setAreaCondizionata(newSistemazioneDTO.areaCondizionata());
            tipoSistemazione.setFrigo(newSistemazioneDTO.frigo());
            tipoSistemazione.setBagnoPrivato(newSistemazioneDTO.bagnoPrivato());
            tipoSistemazione.setCorrenteElettrica(newSistemazioneDTO.correnteElettrica());
            tipoSistemazione.setLavanderia(newSistemazioneDTO.correnteElettrica());
            return tipoSistemazioneRepository.save(tipoSistemazione);
        } else {
            throw new BadRequestException("campeggio inestistente");
        }


    }

    public void findByIdAndDelete(Long id) throws NotFoundException {
        TipoSistemazione foundSistemazione = this.findSistemazioneById(id);
        tipoSistemazioneRepository.delete(foundSistemazione);

    }

    private TipoSistemazione findSistemazioneById(Long id) throws NotFoundException {
        return tipoSistemazioneRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
