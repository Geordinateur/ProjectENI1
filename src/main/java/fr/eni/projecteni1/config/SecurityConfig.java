package fr.eni.projecteni1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

  @Bean
  public BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {

    http.csrf().disable()
      .authorizeHttpRequests((authorize) -> {
        authorize.requestMatchers("/saveUser").permitAll();
        authorize.anyRequest().authenticated();
      }).httpBasic(Customizer.withDefaults());
    return http.build();


  }
  @Bean
  public UserDetailsService userDetailsService(){

    UserDetails ramesh = User.builder()
      .username("ramesh")
      .password(passwordEncoder().encode("password"))
      .roles("USER")
      .build();

    UserDetails admin = User.builder()
      .username("laurent")
      .password(passwordEncoder().encode("laurent"))
      .roles("ADMIN")
      .build();

    return new InMemoryUserDetailsManager(ramesh, admin);
  }


}
