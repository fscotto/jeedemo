package it.plague.jeedemo;

import static org.junit.Assert.*;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import org.junit.Test;

public class CreditCardJSonTest {

  public static final String creditCardJSon =
      "{\"creditCard\":" +
          "{\"number\":\"12345678\"," +
          "\"expiryDate\":\"10/14\"," +
          "\"controlNumber\":566," +
          "\"type\":\"Visa\"}" +
      "}";

  @Test
  public void shouldGenerateACreditCard() {
    CreditCard creditCard = new CreditCard("12345678", "10/14", 566, "Visa");
    StringWriter writer = new StringWriter();
    JsonGenerator generator = Json.createGenerator(writer);
    generator.writeStartObject()
        .writeStartObject("creditCard")
        .write("number", creditCard.getNumber())
        .write("expiryDate", creditCard.getExpiryDate())
        .write("controlNumber", creditCard.getControlNumber())
        .write("type", creditCard.getType())
        .writeEnd()
        .writeEnd()
        .close();
    assertEquals(creditCardJSon, writer.toString().trim());
  }

}
