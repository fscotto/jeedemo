package it.plague.jeedemo;

import javax.jws.WebService;

@WebService(endpointInterface = "it.plague.jeedemo.Validator")
public class CardValidator implements Validator {

  @Override
  public boolean validate(CreditCard creditCard) {
    Character lastDigit = creditCard.getNumber().charAt(creditCard.getNumber().length() - 1);
    return Integer.parseInt(lastDigit.toString()) % 2 == 0;
  }
}
