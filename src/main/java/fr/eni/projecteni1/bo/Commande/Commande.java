package fr.eni.projecteni1.bo.Commande;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.eni.projecteni1.bo.DetailCommande.DetailCommande;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Commande {
  private List<DetailCommande> detailCommandes;

  private Integer id;

  private CommandeStatus status ;
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
  private Date heurePreparation;

  public Commande() {

  }

  @Override
  public String toString() {
    return "Commande{" +
      "detailCommandes=" + detailCommandes +
      ", id=" + id +
      ", heurePreparation=" + heurePreparation +
      '}';
  }

  public Date getHeurePreparation() {
    return heurePreparation;
  }

  public void setHeurePreparation(Date heurePreparation)  {
    this.heurePreparation = heurePreparation;
  }



  public List<DetailCommande> getDetailCommandes() {
    return detailCommandes;
  }

  public void setDetailCommandes(List<DetailCommande> detailCommandes) {
    this.detailCommandes = detailCommandes;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CommandeStatus getStatus() {
    return status;
  }

  public void setStatus(CommandeStatus status) {
    this.status = status;
  }
}
