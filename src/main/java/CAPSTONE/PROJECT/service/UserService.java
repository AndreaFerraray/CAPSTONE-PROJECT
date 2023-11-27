package CAPSTONE.PROJECT.service;

import CAPSTONE.PROJECT.entities.User;
import CAPSTONE.PROJECT.exceptions.NotFoundException;

import CAPSTONE.PROJECT.exceptions.NotFoundNameException;
import CAPSTONE.PROJECT.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
    @Autowired
    PasswordEncoder bcrypt;
    @Autowired
    UserRepository userRepository;



    public Page<User> getAllUsers(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }


    public User findUserById(long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

public Page<User> findForName(String nome,int page,int size) throws NotFoundNameException {
Pageable pageable = PageRequest.of(page,size);
    Page<User> result = userRepository.findForName(nome, pageable);
    if (result.isEmpty()) {
        throw new NotFoundNameException(nome);
    }

    return result;
}






}
