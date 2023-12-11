package CAPSTONE.PROJECT.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="Campeggi")
public class Campeggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String password;
    private long postiDisp;
    private String indirizzo;
    private String email;
    private String descrizione;
    private long numeroTelefono;
    private boolean caniAmmessi;
    private boolean piscina;
    private boolean animazione;
    private boolean market;
    private boolean ristorante;
    private long stelle;
    private String logo;



   /* @ManyToMany
    @JsonIgnore
    @JoinTable(name = "user_campeggio_recensioni",
            joinColumns  = @JoinColumn(name = "campeggio_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
            private Set<User> user= new HashSet<>();*/


    @ManyToMany
@JsonIgnore
    @JoinTable(name = "preferiti",
            joinColumns  = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "campeggio_id"))
            private Set<User> userFavorite = new HashSet<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "prenotazioni",
            joinColumns  = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "campeggio_id"))
    private List<User> prenotazioni = new ArrayList<>();

}
