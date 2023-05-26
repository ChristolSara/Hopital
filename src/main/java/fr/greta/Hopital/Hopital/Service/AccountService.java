package fr.greta.Hopital.Hopital.Service;

import fr.greta.Hopital.Hopital.Model.AppRole;
import fr.greta.Hopital.Hopital.Model.AppUser;

public interface AccountService {
    AppUser addNewUser(String username,String password,String email,String confirmedPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String role);
    AppUser loadUserByUsername(String username);

}
