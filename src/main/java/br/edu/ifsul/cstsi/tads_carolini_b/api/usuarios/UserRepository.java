package br.edu.ifsul.cstsi.tads_carolini_b.api.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByLogin(String login);
}
