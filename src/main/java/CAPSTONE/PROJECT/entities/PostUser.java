package CAPSTONE.PROJECT.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class PostUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private long postId;
    private String testo ;
    private String foto;
    private LocalDate dataPubblicazione;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User userPost;


}
