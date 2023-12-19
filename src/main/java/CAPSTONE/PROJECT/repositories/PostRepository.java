package CAPSTONE.PROJECT.repositories;

import CAPSTONE.PROJECT.entities.PostUser;
import CAPSTONE.PROJECT.entities.User;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<PostUser,Long> {

   /* @Query("SELECT p FROM PostUser p WHERE p.userPost = :user_id")
    Page<User> findByUserId(@Param("PostUser") User user_id, Pageable pageable);*/

}
