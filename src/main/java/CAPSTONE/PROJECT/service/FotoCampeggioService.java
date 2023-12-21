package CAPSTONE.PROJECT.service;

import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.entities.FotoCampeggio;
import CAPSTONE.PROJECT.payload.NewFotoCampeggioDTO;
import CAPSTONE.PROJECT.repositories.CampeggioRepository;
import CAPSTONE.PROJECT.repositories.FotoCampeggioRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class FotoCampeggioService {
    @Autowired
    CampeggioRepository campeggioRepository;
    @Autowired
    Cloudinary cloudinary;
    @Autowired
    FotoCampeggioRepository fotoCampeggioRepository;
    public Campeggio addFoto(NewFotoCampeggioDTO newFotoCampeggioDTO) throws IOException {
        String cloudinaryURL = (String) cloudinary.uploader().upload(newFotoCampeggioDTO.foto().getBytes(), ObjectUtils.emptyMap()).get("url");
        Campeggio campeggio = campeggioRepository.findCampeggioById(newFotoCampeggioDTO.idCampeggio());
    FotoCampeggio fotoCampeggio = new FotoCampeggio();
    fotoCampeggio.setCampeggio(campeggio);
    fotoCampeggio.setFoto(cloudinaryURL);
    fotoCampeggioRepository.save(fotoCampeggio);
            return campeggio;
    }
}
