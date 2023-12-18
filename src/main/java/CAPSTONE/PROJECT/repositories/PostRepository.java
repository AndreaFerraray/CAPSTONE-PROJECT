package CAPSTONE.PROJECT.repositories;

import CAPSTONE.PROJECT.entities.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostUser,Long> {
}
