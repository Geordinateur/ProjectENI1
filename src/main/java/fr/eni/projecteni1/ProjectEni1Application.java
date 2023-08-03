package fr.eni.projecteni1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjectEni1Application {


  public static void main(String[] args) {

    SpringApplication.run(ProjectEni1Application.class, args);

  }
}
