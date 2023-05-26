package fr.greta.Hopital.Hopital.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AppUser {
    @Id
    private String userId;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;

    //il charge pas la liste de role que si o a besoin
    //
    // @ManyToMany(fetch  = FetchType.LAZY)

    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles;
}
