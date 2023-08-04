package fr.eni.projecteni1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {

    http.csrf()
      .disable()
      .authorizeHttpRequests((authorize) -> {
        authorize.requestMatchers("/Produits").permitAll();
        authorize.requestMatchers("/SaveProduit").hasAnyRole("EMPLOYE", "CUISINIER", "GERANT");
        authorize.requestMatchers("/UpdateProduit/{id}").hasAnyRole("EMPLOYE", "CUISINIER", "GERANT");
        authorize.requestMatchers("/DeleteProduit/{id}").hasAnyRole("EMPLOYE", "CUISINIER", "GERANT");
        authorize.requestMatchers("/deleteOrder/{id}").hasAnyRole("EMPLOYE", "CUISINIER", "GERANT");
        authorize.requestMatchers("/updateOrder/{id}").hasAnyRole("EMPLOYE", "CUISINIER", "GERANT");
        authorize.requestMatchers("/orderById/{id}").hasAnyRole("EMPLOYE", "CUISINIER", "GERANT");
        authorize.requestMatchers("/allOrders").hasAnyRole("EMPLOYE", "CUISINIER", "GERANT");
        authorize.requestMatchers("/saveUser").permitAll();
        authorize.requestMatchers("/login").permitAll();
        authorize.requestMatchers("/deleteUser/{id}").hasRole("GERANT");
        authorize.requestMatchers("/getUser/{userName}").hasAnyRole("CLIENT", "EMPLOYE", "GERANT");
        authorize.requestMatchers("/orderById/{id}").hasAnyRole("EMPLOYE", "CUISINIER", "GERANT");
        authorize.anyRequest().authenticated();
      }).httpBasic(Customizer.withDefaults());
    return http.build();


  }
}
