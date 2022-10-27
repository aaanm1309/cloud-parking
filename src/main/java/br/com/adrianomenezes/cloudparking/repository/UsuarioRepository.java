package br.com.adrianomenezes.cloudparking.repository;

import br.com.adrianomenezes.cloudparking.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    @Query("SELECT e FROM Usuario e JOIN FETCH e.roles WHERE e.username= (:username)")
    public Usuario findByUsername(@Param("username") String username);

    boolean existsByUsername(String username);

}
