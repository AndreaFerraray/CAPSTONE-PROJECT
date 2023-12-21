package CAPSTONE.PROJECT.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="FotoCampeggio")
public class FotoCampeggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String foto;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "campeggio_id")
    private Campeggio campeggio;
}

