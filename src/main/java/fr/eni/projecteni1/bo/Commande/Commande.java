package fr.eni.projecteni1.bo.Commande;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.eni.projecteni1.bo.DetailCommande.DetailOrder;

import java.util.Date;
import java.util.List;

public class Commande {
  private List<DetailOrder> detailOrders;

  private Integer id;

  private CommandeStatus status;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
  private Date heurePreparation;

  public Commande() {

  }

  @Override
  public String toString() {
    return "Commande{" +
      "detailCommande=" + detailOrders +
      ", id=" + id +
      ", heurePreparation=" + heurePreparation +
      '}';
  }

  public Date getHeurePreparation() {
    return heurePreparation;
  }

  public void setHeurePreparation(Date heurePreparation) {
    this.heurePreparation = heurePreparation;
  }


  public List<DetailOrder> getDetailorders() {
    return detailOrders;
  }

  public void setDetailOrders(List<DetailOrder> detailOrders) {
    this.detailOrders = detailOrders;
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
