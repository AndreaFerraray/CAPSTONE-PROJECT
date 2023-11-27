package CAPSTONE.PROJECT.repositories;

import CAPSTONE.PROJECT.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {
    Page<User> findForName(String nome, Pageable pageable);


}
