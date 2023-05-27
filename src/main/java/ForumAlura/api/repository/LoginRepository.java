package ForumAlura.api.repository;

import ForumAlura.api.entity.login.LoginUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginRepository extends JpaRepository<LoginUsuario,Long> {

    UserDetails findByLogin(String login);
}
