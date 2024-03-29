package CAPSTONE.PROJECT.service;

import CAPSTONE.PROJECT.entities.*;
import CAPSTONE.PROJECT.exceptions.BadRequestException;
import CAPSTONE.PROJECT.exceptions.NotFoundException;

import CAPSTONE.PROJECT.exceptions.NotFoundNameException;
import CAPSTONE.PROJECT.payload.NewPostDTO;
import CAPSTONE.PROJECT.payload.NewPrenotazioneDTO;
import CAPSTONE.PROJECT.payload.NewUserDTO;
import CAPSTONE.PROJECT.repositories.CampeggioRepository;
import CAPSTONE.PROJECT.repositories.PostRepository;
import CAPSTONE.PROJECT.repositories.PrenotazioneRepository;
import CAPSTONE.PROJECT.repositories.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.mail.Multipart;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    PrenotazioneRepository prenotazioneRepository;

@Autowired
    Cloudinary cloudinary;
    @Autowired
    PasswordEncoder bcrypt;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    CampeggioRepository campeggioRepository;
    @Value("${spring.mail.receiver}")
    private String email;
    @Autowired
    private PostRepository postRepository;


    public User saveUser(NewUserDTO newUserDTO) throws IOException {
        userRepository.findByEmail(newUserDTO.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });
        userRepository.findByCognome(newUserDTO.username()).ifPresent(user -> {
            throw new BadRequestException(
                    "Lo username " + user.getCognome() + " è già utilizzato!");
        });
        User newUser = new User();
        newUser.setUsername(newUserDTO.username());
        newUser.setRole(Role.USER);
        newUser.setNome(newUserDTO.nome());
        newUser.setCognome(newUserDTO.cognome());
        newUser.setEmail(newUserDTO.email());
        newUser.setPassword(bcrypt.encode(newUserDTO.password()));
        newUser.setImgProfilo("https://ui-avatars.com/api/?name=" + newUserDTO.nome() + "+" + newUserDTO.cognome());
        emailService.sendEmail(email, "account creato correttamente", "benvenuto " + newUser.getUsername() + " il tuo account e' stato creato con successo");
        return userRepository.save(newUser);
    }



    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User con email " + email + " non trovato!"));
    }




    public Page<User> getAllUsers(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }




    public User findUserById(long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }



public Page<User> findByNome(String nome,int page,int size) throws NotFoundNameException {
Pageable pageable = PageRequest.of(page,size);
    Page<User> result = userRepository.findByNome(nome, pageable);
    if (result.isEmpty()) {
        throw new NotFoundNameException(nome);
    }

    return result;
}




    public void findUserByIdAndDelete(long id) throws NotFoundException {
        User user= this.findUserById(id);
        userRepository.delete(user);
    }



    private PostUser findById(long id)
       throws NotFoundException {
            return postRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        }



    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User addFavorite(User user, Long campeggioId) {
        User existingUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        Campeggio campeggio = campeggioRepository.findById(campeggioId)
                .orElseThrow(() -> new EntityNotFoundException("Campeggio non trovato"));

        existingUser.addCampeggioPreferito(campeggio);
        return userRepository.save(existingUser);
    }
    public User deleteFavorite(User user, Long campeggioId) {
        User existingUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        Campeggio campeggio = campeggioRepository.findById(campeggioId)
                .orElseThrow(() -> new EntityNotFoundException("Campeggio non trovato"));

        existingUser.removeCampeggioPreferito(campeggio);
        System.out.println(existingUser);
       return userRepository.save(existingUser);
    }

    public User uploadPicture(MultipartFile file, long id) throws IOException {
        User foundUser = this.findUserById(id);
        String cloudinaryURL = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        foundUser.setImgProfilo(cloudinaryURL);
        return userRepository.save(foundUser);
    }


    public User addPost(User user , NewPostDTO newPostDTO, LocalDate currentDate) throws IOException {
        String cloudinaryURL = (String) cloudinary.uploader().upload(newPostDTO.file().getBytes(), ObjectUtils.emptyMap()).get("url");

            PostUser post = new PostUser();
            post.setUserPost(user);
            post.setTesto(newPostDTO.testo());
            post.setFoto(cloudinaryURL);
            post.setDataPubblicazione(currentDate);

            postRepository.save(post);
            return user.addPostUser(post);
    }

    public User deletePost(User user, long postId) {
        User existingUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        PostUser post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("post non trovato"));

        existingUser.removePostUser(post);
        postRepository.delete(post);
        return userRepository.save(existingUser);
    }


}
