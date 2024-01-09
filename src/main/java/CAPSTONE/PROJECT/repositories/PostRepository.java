package CAPSTONE.PROJECT.repositories;

import CAPSTONE.PROJECT.entities.PostUser;
import CAPSTONE.PROJECT.entities.User;
import feign.Param;
import jakarta.persistence.OrderBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<PostUser,Long> {

    @OrderBy("dataPubblicazione DESC")
    Page<PostUser> findAll(Pageable pageable);

}
