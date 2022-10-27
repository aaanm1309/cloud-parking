package br.com.adrianomenezes.cloudparking.init;

import br.com.adrianomenezes.cloudparking.entity.Usuario;
import br.com.adrianomenezes.cloudparking.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StartApplication implements CommandLineRunner {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Usuario user = repository.findByUsername("admin");
        if(user==null){
            user = new Usuario();
            user.setName("ADMIN");
            user.setUsername("admin");
            user.setPassword(encoder.encode("master123"));
            System.out.println(encoder.encode("master123"));
            user.getRoles().add("MANAGERS");
            repository.save(user);
        }
        user = repository.findByUsername("user");
        if(user ==null){
            user = new Usuario();
            user.setName("USER");
            user.setUsername("user");
            user.setPassword(encoder.encode("user123"));
            user.getRoles().add("USERS");
            repository.save(user);
        }
    }
}