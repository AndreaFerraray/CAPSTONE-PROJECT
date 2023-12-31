package CAPSTONE.PROJECT.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "prenotazione")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "data_prenotazione")
    private LocalDate dataPrenotazione;
    @Column(name = "data_check_in")
    private LocalDate data_check_in;
    @Column(name = "data_check_out")
    private LocalDate data_check_out;

    @Column(name = "ospiti")
    private Long ospiti;

    @Column(name="cani")
    private boolean cani;

    @Column(name = "totale")
    private Long costoTotale;

    @ManyToOne
    @JoinColumn(name = "tipo_sistemazione_id")
    private TipoSistemazione tipoSistemazione;



    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "campeggio_id")
    private Campeggio campeggio;
}
