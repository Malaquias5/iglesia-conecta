package com.iacym.config;

import com.iacym.entity.Usuario;
import com.iacym.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner crearAdmin(UsuarioRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.count() == 0) {
                Usuario u = new Usuario();
                u.setNombres("Administrador");
                u.setApellidos("Principal");
                u.setCorreo("admin@iglesia.com");
                u.setPassword(encoder.encode("admin123"));
                u.setRol(Usuario.Rol.ADMINISTRADOR);
                repo.save(u);
            }
        };
    }
}