package it.plague.jeedemo;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  @NotNull
  private String street1;
  private String street2;
  @NotNull
  private String city;
  private String state;
  @NotNull
  @ZipCode
  private String zipCode;
  private String coutry;

  public Address(@NotNull String street1, @NotNull String city, String state,
      @NotNull String zipCode, String coutry) {
    this.street1 = street1;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.coutry = coutry;
  }
}
