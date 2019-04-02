package it.plague.jeedemo;

import javax.jws.WebService;

@WebService
public interface Validator {

  boolean validate(CreditCard creditCard);
}
