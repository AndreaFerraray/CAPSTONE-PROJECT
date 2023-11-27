package CAPSTONE.PROJECT.controller;

import CAPSTONE.PROJECT.entities.User;
import CAPSTONE.PROJECT.exceptions.BadRequestException;
import CAPSTONE.PROJECT.payload.NewUserDTO;
import CAPSTONE.PROJECT.payload.SeccessfullLoginDTO;
import CAPSTONE.PROJECT.payload.UserLoginDTO;
import CAPSTONE.PROJECT.service.AuthService;
import CAPSTONE.PROJECT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    User saveUser(@RequestBody NewUserDTO newUserDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw  new BadRequestException(validation.getAllErrors());
        }
        else{
            try {
                return userService.saveUser(newUserDTO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    @PostMapping("/login")
    public SeccessfullLoginDTO SeccessfullLoginDTO(@RequestBody UserLoginDTO userLoginDTO){
        return new SeccessfullLoginDTO(authService.authUser(userLoginDTO));

    }






}
