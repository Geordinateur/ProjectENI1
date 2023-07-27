package fr.eni.projecteni1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectEni1Application {

  public static void main(String[] args) {

    SpringApplication.run(ProjectEni1Application.class, args);
    //Produit produit = new Produit("Pizza forestiere", 10.5f, EnumProductType.PIZZA);
    //System.out.println("le produit créer est : " + produit);

  }
}
