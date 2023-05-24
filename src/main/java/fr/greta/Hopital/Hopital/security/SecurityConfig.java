package fr.greta.Hopital.Hopital.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

//in memory authentication
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(

                User.withUsername("user1").password(passwordEncoder.encode("1235")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1235")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("ADMIN","USER").build()

        );
        return inMemoryUserDetailsManager;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                   http.formLogin();
                   http.authorizeHttpRequests().anyRequest().authenticated();

                       return    http.build();
    }



}
