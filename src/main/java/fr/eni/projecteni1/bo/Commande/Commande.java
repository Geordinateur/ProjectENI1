package fr.eni.projecteni1.bo.Commande;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.eni.projecteni1.bo.DetailCommande.DetailOrder;

import java.time.LocalDateTime;
import java.util.List;

public class Commande {
  private List<DetailOrder> detailOrders;

  private Integer id;

  private CommandeStatus status;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "UTC+2")
  private LocalDateTime heurePreparation;

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

  public LocalDateTime getHeurePreparation() {
    return heurePreparation;
  }

  public void setHeurePreparation(LocalDateTime heurePreparation) {
    this.heurePreparation = heurePreparation;
  }


  public List<DetailOrder> getDetailOrders() {
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
