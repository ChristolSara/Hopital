package fr.greta.Hopital.Hopital.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min= 2 , max = 23)
    private String name;
    @Temporal(TemporalType.DATE)//pour stoker que la date et non le temp
    private Date dateNaissance;
    private boolean malade;
    @Min(10)
    private int score;
}
