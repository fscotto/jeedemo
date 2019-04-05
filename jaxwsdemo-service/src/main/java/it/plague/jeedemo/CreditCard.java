package it.plague.jeedemo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditCard {

  @XmlAttribute(required = true)
  private String number;

  @XmlAttribute(name = "expiry_date", required = true)
  private String expiryDate;

  @XmlAttribute(name = "control_number", required = true)
  private Integer controlNumber;

  @XmlAttribute(required = true)
  private String type;

}
