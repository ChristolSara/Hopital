package fr.greta.Hopital.Hopital;

import fr.greta.Hopital.Hopital.Model.Patient;
import fr.greta.Hopital.Hopital.Repository.PatientRepository;
import fr.greta.Hopital.Hopital.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class HopitalApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HopitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	//	patientRepository.save(new Patient(null,"sara",new Date(),true,35));


    }
    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){

        return args -> {
            //creer des roles
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            //creer des users
            accountService.addNewUser("admin","admin","admin@gmail.com","admin");
            accountService.addNewUser("user11","123456","user1@gmail.com","123456");
            accountService.addNewUser("user22","123","user2@gmail.com","222");
            //accoerder un role à un  user

            accountService.addRoleToUser("user11","USER");
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("user22","USER");


        };

    }

    //pour cripter le password
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
