package sistema_de_gestao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_de_gestao.entities.User;
import sistema_de_gestao.services.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/user") //define que esse controlador sera conectado com o frontend via conexao API, mas ou menos isso
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    @Autowired
    private UserService userService;
    /*
    @GetMapping
    public ResponseEntity<User> getUserById(int userId){
        logger.info("Buscando usuario pelo id:"+userId);

        Optional<User> userOptional = userService.getUserById(userId);
        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
        }
    }*/


    @GetMapping
    public ResponseEntity<User> getUserByEmail(@RequestParam String email){
        logger.info("Buscando o usuario pelo username "+ email);

        Optional<User> userOptional = userService.getUserByEmail(email);
        if(userOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String username,
                                       @RequestParam String password) {
        Optional<User> userOptional = userService.login(username, password);

        if (userOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("erro", "Credenciais inválidas"));

        User user = userOptional.get();

        // retorna os dados do utilizador para o JavaFX guardar na sessão
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("id", user.getId());
        resposta.put("nome", user.getNome());
        resposta.put("username", user.getUsername());
        resposta.put("perfil", user.getProfile());

        return ResponseEntity.ok(resposta);
    }

    /*
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user){
        logger.info("Criando novo usuario");

        User newUser = userService.createUser(
                user.getNome(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword()
        );
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }*/
    @PostMapping("/register")
    public ResponseEntity<User> createUser(
            @RequestParam String nome,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password) {

        logger.info("Criando novo usuario: " + username);

        User newUser = userService.createUser(nome, username, email, password);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

}
