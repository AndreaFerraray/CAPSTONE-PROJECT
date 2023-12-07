package CAPSTONE.PROJECT.controller;

import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.entities.User;
import CAPSTONE.PROJECT.exceptions.NotFoundException;
import CAPSTONE.PROJECT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    Page <User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size){
        return userService.getAllUsers(page,size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    User findUserById(@PathVariable long id){
        return userService.findUserById(id);
}

@GetMapping("{nome}")
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    Page<User> getForName(@PathVariable String nome, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size){
        return userService.findByNome(nome,page,size);
}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    void findUserByIdAndDelete(@PathVariable long id) {
        userService.findUserByIdAndDelete(id);}
@DeleteMapping("/me")
@ResponseStatus(HttpStatus.NO_CONTENT)
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    void deleteMyProfile(@AuthenticationPrincipal User loggedUser){
        userService.findUserByIdAndDelete(loggedUser.getUserId());
}


    @PatchMapping("/upload")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public User uploadAvatar(@RequestParam("avatar") MultipartFile body, @AuthenticationPrincipal User loggedUser) throws IOException {
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return userService.uploadPicture(body, loggedUser.getUserId());
    }

    @GetMapping("/me")
    public UserDetails getLoggedProfile(@AuthenticationPrincipal UserDetails loggedUser) {
        return loggedUser;
    }

@PostMapping("/addFavorite")
public ResponseEntity<String> addFavorite(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Long campeggioId){
    if (userDetails != null) {
        User user = userService.findUserByUsername(userDetails.getUsername());
        userService.addFavorite(user, campeggioId);
        return ok("Campeggio aggiunto ai preferiti con successo");
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autenticato");
    }
}

@DeleteMapping("/deleteFavorite")
public ResponseEntity<String> deleteFavorite(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Long campeggioId){
    if (userDetails != null) {
        User user = userService.findUserByUsername(userDetails.getUsername());
        userService.deleteFavorite(user, campeggioId);
        return ok("Campeggio eliminato con successo");
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autenticato");
    }
}


@GetMapping("/favorite")
public ResponseEntity<Page<Campeggio>>getPreferitiUtente(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size){

    if (userDetails == null) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);}

    User user = userService.findUserByUsername(userDetails.getUsername());
        Set<Campeggio> preferiti = user.getCampeggioPreferito();
       List<Campeggio> preferitiList = new ArrayList<>(preferiti);
        int start = page * size;
        int end = Math.min(start + size, preferitiList.size());


        List<Campeggio> pageContent = preferitiList.subList(start, end);
        Page<Campeggio> preferitiPage = new PageImpl<>(pageContent, PageRequest.of(page, size), preferitiList.size());

        return ResponseEntity.ok(preferitiPage);
    }



}
