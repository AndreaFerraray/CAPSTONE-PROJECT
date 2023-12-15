package CAPSTONE.PROJECT.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class TipoSistemazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeSistemazione;
    private Long personeAmmesse;
    private Long prezzoNotte;
    private Boolean areaCondizionata;
    private Boolean frigo;
    private Boolean bagnoPrivato;
    private Boolean correnteElettrica;
    private Boolean lavanderia;

    @ManyToOne
    @JoinColumn(name = "campeggio_id")
    private Campeggio campeggioSistemazione;

    @OneToMany(mappedBy = "tipoSistemazione")
    @JsonIgnore
    private List<Prenotazione> prenotazioni;

    public TipoSistemazione() {
    }
}
