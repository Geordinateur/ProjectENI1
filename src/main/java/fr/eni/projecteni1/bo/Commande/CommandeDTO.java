package fr.eni.projecteni1.bo.Commande;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.eni.projecteni1.bo.DetailCommande.DetailOrderDTO;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class CommandeDTO {
  private List<DetailOrderDTO> detailOrder;

  private Integer id;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "UTC+2")
  private LocalDateTime heurePreparation;

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

  public LocalDateTime getHeurePreparation() {
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

  public void setHeurePreparation(LocalDateTime heurePreparation) {
    this.heurePreparation = heurePreparation;
  }
}
