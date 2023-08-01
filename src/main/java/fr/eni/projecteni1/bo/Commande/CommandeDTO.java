package fr.eni.projecteni1.bo.Commande;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.eni.projecteni1.bo.DetailCommande.DetailOrderDTO;

import java.util.Date;
import java.util.List;

public class CommandeDTO {
  private List<DetailOrderDTO> detailOrder;

  private Integer id;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
  private Date heurePreparation;

  private String status;


  public CommandeDTO() {
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<DetailOrderDTO> getDetailOrder() {
    return detailOrder;
  }

  public void setDetailOrder(List<DetailOrderDTO> detailOrder) {
    this.detailOrder = detailOrder;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getHeurePreparation() {
    return heurePreparation;
  }

  @Override
  public String toString() {
    return "CommandeDTO{" +
            "detailOrder=" + detailOrder +
            ", id=" + id +
            ", heurePreparation=" + heurePreparation +
            '}';
  }

  public void setHeurePreparation(Date heurePreparation) {
    this.heurePreparation = heurePreparation;
  }
}
