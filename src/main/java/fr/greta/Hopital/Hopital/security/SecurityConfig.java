package fr.greta.Hopital.Hopital.security;

import fr.greta.Hopital.Hopital.Service.UserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity(prePostEnabled = true) //potection basé sur globale method security
public class SecurityConfig {
    private PasswordEncoder passwordEncoder;
    private UserDetailServiceImpl userDetailsServiceImpl;



  //  @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return  new JdbcUserDetailsManager(dataSource);
    }

//in memory authentication
   // @Bean
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

                   //donner l'authorisation  de supp que à l'admin
                  //http.authorizeHttpRequests().requestMatchers("/admin/**").hasAnyRole("ADMIN");
//                   http.authorizeHttpRequests().requestMatchers("/user/**").hasAnyRole("USER");
                   http.authorizeHttpRequests().anyRequest().authenticated();

                   //userDetailsServices

                   http.userDetailsService(userDetailsServiceImpl);
                   //envoyer vers une page d'erreur
                   http.exceptionHandling().accessDeniedPage("/403");

                       return    http.build();
    }



}
