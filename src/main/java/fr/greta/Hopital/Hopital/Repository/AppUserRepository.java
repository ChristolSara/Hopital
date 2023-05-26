package fr.greta.Hopital.Hopital.Repository;

import fr.greta.Hopital.Hopital.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
