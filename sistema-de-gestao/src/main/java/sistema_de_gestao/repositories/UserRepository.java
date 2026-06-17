package sistema_de_gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_de_gestao.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsernameOrEmail (String username, String email);
    void deleteByUsername (String username, String email);

    // Achar o usuario por meio de emai

}
