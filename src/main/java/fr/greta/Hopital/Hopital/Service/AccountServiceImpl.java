package fr.greta.Hopital.Hopital.Service;

import fr.greta.Hopital.Hopital.Model.AppRole;
import fr.greta.Hopital.Hopital.Model.AppUser;
import fr.greta.Hopital.Hopital.Repository.AppRoleRepository;
import fr.greta.Hopital.Hopital.Repository.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
//permet de mettree a jour les operations vers la bdd
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmedPassword) {
        AppUser  appUser = appUserRepository.findByUsername(username);

        //si le user exist deja!!
        if(appUser != null) throw new RuntimeException("this user already exist");
        // si le mot de pass n'est pas just
        if(!password.equals(confirmedPassword)) throw new RuntimeException("password not match ");
        //on cree un nouveau user
         appUser =AppUser.builder()
                 .userId(UUID.randomUUID().toString())
                 .username(username)
                 .password(passwordEncoder.encode(password))
                 .email(email)
                 .build();
       AppUser savedAppUser =  appUserRepository.save(appUser);
        return savedAppUser;
    }
//ajouter un role
    @Override
    public AppRole addNewRole(String role) {
        //verifeir si le role exist
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if(appRole !=null)throw  new RuntimeException("this role already exist");

        //creer le role
        appRole = AppRole.builder()
                .role(role)
                .build();

        return appRoleRepository.save(appRole);
    }

    //associer  un role à un user
     @Override
    public void addRoleToUser(String username, String role) {

        AppRole appRole = appRoleRepository.findById(role).get();
        AppUser appUser = appUserRepository.findByUsername(username);

        appUser.getRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String username, String role) {


        AppRole appRole = appRoleRepository.findById(role).get();
        AppUser appUser = appUserRepository.findByUsername(username);

        appUser.getRoles().remove(appRole);

    }

    @Override
    public AppUser loadUserByUsername(String username) {
        AppUser appUser = appUserRepository.findByUsername(username);
        return appUser;
    }
}
