package fr.eni.projecteni1.service;

import fr.eni.projecteni1.repository.DetailCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailCommandeService {

  @Autowired
  private DetailCommandeRepository detailCommanderrepository;

  public String getDetailCommandeService(){
    return this.detailCommanderrepository.createDetailCommande();
  }

}
