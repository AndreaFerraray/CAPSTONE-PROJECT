package CAPSTONE.PROJECT.entities;

import CAPSTONE.PROJECT.repositories.PrenotazioneRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "prenotazione")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "campeggio_id")
    private Campeggio campeggio;

    @Column(name = "data_prenotazione")
    private Date dataPrenotazione;
    @Column(name = "data_check-in")
    private Date data_check_in;
    @Column(name = "data_check_out")
    private Date data_check_out;

    @Column(name = "ospiti")
    private Long ospiti;
    @Column(name = "notti")
    private Long notti;



}
