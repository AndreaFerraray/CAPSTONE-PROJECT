package CAPSTONE.PROJECT.controller;

import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.entities.FotoCampeggio;
import CAPSTONE.PROJECT.payload.NewFotoCampeggioDTO;
import CAPSTONE.PROJECT.service.FotoCampeggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("fotoCampeggio")
public class FotoCampeggioController {
    @Autowired
    FotoCampeggioService fotoCampeggioService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    Campeggio addFoto(@ModelAttribute NewFotoCampeggioDTO newFotoCampeggioDTO)throws IOException {
        return fotoCampeggioService.addFoto(newFotoCampeggioDTO);
    }


}
