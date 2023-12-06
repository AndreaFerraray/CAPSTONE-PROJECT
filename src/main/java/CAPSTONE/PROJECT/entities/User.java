package CAPSTONE.PROJECT.entities;

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

    @ManyToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Campeggio> campeggio= new HashSet<>();



    @ManyToMany
    @JoinTable(
            name = "user_campeggio",
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




    public void addPreferiti(Campeggio campeggioId) {

    }

    public void addCampeggioPreferito(Campeggio campeggio) {
        campeggioPreferito.add(campeggio);
    }
}





