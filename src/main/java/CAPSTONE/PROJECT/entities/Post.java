package CAPSTONE.PROJECT.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long postId;
    private String foto;
    private String content;
    private String luogo;


}
