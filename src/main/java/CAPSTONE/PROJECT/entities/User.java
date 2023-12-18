package CAPSTONE.PROJECT.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"password", "authorities", "credentialsNonExpired", "accountNonExpired", "accountNonLocked", "enabled"})
@Table(name = "User")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private long userId;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String username;
    private long tel_number;
    private String imgProfilo;


    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "user")
    private Set<Prenotazione> prenotazioni;


@OneToMany(mappedBy = "userPost")
private List<PostUser> post;

    @ManyToMany
    @JoinTable(
            name = "preferiti",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "campeggio_id")
    )
    private Set<Campeggio> campeggioPreferito = new HashSet<>();




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }





    public void addCampeggioPreferito(Campeggio campeggio) {
        campeggioPreferito.add(campeggio);
    }

    public void removeCampeggioPreferito(Campeggio campeggio) {
        campeggioPreferito.remove(campeggio);
    }



    public void deleteOneBooking(Campeggio campeggio) {
        prenotazioni.remove(campeggio);
    }

    public User addBooking(Prenotazione prenotazione) {
        this.prenotazioni.add(prenotazione);
        User user= prenotazione.getUser();
        return user;
    }
    public User addPostUser(PostUser post) {
        this.post.add(post);
        User user = post.getUserPost();
        return user;
    }
    public Long getId() {
        return userId;
    }

    public void removePostUser(PostUser post) {
        this.post.remove(post);
        post.setUserPost(null);
    }
}





