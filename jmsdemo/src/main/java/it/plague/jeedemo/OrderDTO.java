package it.plague.jeedemo;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable {

  private Long orderId;
  private Date creationDate;
  private String customerName;
  private Float totalAmount;
}
