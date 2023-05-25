package fr.greta.Hopital.Hopital.Service;

import fr.greta.Hopital.Hopital.Model.AppUser;

public interface AccountService {
    AppUser addNewUser(String username,String password,String email,String confirmedPassword);

}
