package CAPSTONE.PROJECT.controller;

import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.payload.NewCampeggioDTO;
import CAPSTONE.PROJECT.service.CampeggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/campeggi")
public class CampeggioController {
@Autowired
CampeggioService campeggioService;




    @GetMapping("")
    Page <Campeggio> getCampeggi(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String orderBy){
        return campeggioService.getCampeggi(page,size, orderBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Campeggio saveCampeggio(@Validated @RequestBody NewCampeggioDTO newCampeggioDTO ) throws IOException {
        return campeggioService.saveCampeggio(newCampeggioDTO);}





}
