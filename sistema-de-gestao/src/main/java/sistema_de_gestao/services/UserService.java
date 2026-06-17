package sistema_de_gestao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import sistema_de_gestao.entities.User;
import sistema_de_gestao.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    @Autowired // faz uma copia da entidade do banco de dados e crie a conexao dessa classe com a classe userService
    private UserRepository userRepository;

    public Optional<User> getUserById(int userId){
        logger.info("Buscando o usuario pelo id: " + userId);
        return userRepository.findById(userId);
    }

    /*
    public Optional<User> getUserByUsername(@RequestParam String username){
        logger.info("Buscando o usuario pelo username: "+username);
        return userRepository.findByUsername(username);
    }*/

    // puxa o atribzxuto de email do database da entidade utilizador
    public Optional<User> getUserByEmail(@RequestParam String email){
        logger.info("Buscando o usuario pelo email: "+email);
        return userRepository.findByUsernameOrEmail(email, null);
    }

    public Optional<User> login(@RequestParam String usernameOrEmail, @RequestParam String Password ){
        logger.info("Tentativa de login: " + usernameOrEmail);

        Optional<User> user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if(user.isEmpty()){
            logger.warning("User not found: "+usernameOrEmail);
            return Optional.empty();
        }

        if(!user.get().getPassword().equals(Password)){
            logger.warning("Palavra passe incorrecta: "+usernameOrEmail); // mensagem de erro no servidor
            return Optional.empty();
        }

        logger.info("Login com successo: "+usernameOrEmail); // informa no terminal quando login e feito com sucesso
        return user;
    }

    // Post
    public User createUser(String name, String username, String email, String password){
        User user = new User();
        user.setNome(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
}
