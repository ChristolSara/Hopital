package fr.greta.Hopital.Hopital;

import fr.greta.Hopital.Hopital.Model.Patient;
import fr.greta.Hopital.Hopital.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		patientRepository.save(new Patient(null,"sara",new Date(),true,35));


    }

}
