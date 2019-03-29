package it.plague.jeedemo;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @NotNull
  @Size(min = 2)
  private String firstName;
  private String lastName;
  @Email
  private String email;
  private String phoneNumber;
  @Past
  private Date dateOfBirth;
  private Address deliveryAddress;

  public Customer(
      @NotNull @Size(min = 2) String firstName, String lastName, String email) {
    this(firstName, lastName, email, null, null, null);
  }
}
