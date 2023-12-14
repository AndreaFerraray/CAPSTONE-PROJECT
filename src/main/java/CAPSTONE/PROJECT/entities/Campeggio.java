package CAPSTONE.PROJECT.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private List<String> immagini;



    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "preferiti",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "campeggio_id"))
    private Set<User> userFavorite = new HashSet<>();

    @OneToMany(mappedBy = "campeggio")
    @ToString.Exclude
    @JsonIgnore
    private Set<Prenotazione> prenotazione;

    @OneToMany(mappedBy ="campeggioSistemazione")
    @JsonIgnore
    private List<TipoSistemazione> tipoSistemazione = new ArrayList<>();

}
