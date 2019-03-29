package it.plague.jeedemo;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.Test;

public class CreditCardXmlTest {

  public static final String creditCardXml =
      "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
          "<creditCard number=\"12345678\">" +
          "<expiry_date>10/14</expiry_date>" +
          "<control_number>566</control_number>" +
          "<type>Visa</type>" +
          "</creditCard>";

  @Test
  public void shouldMarshallACreditCard() throws JAXBException {
    CreditCard creditCard = new CreditCard("12345678", "10/14", 566, "Visa");
    StringWriter writer = new StringWriter();
    JAXBContext context = JAXBContext.newInstance(CreditCard.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.marshal(creditCard, writer);
    System.out.println(writer);
    assertEquals(creditCardXml, writer.toString().trim());
  }

  @Test
  public void shouldUnmarshallACreditCard() throws JAXBException {
    StringReader reader = new StringReader(creditCardXml);
    JAXBContext context = JAXBContext.newInstance(CreditCard.class);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    CreditCard creditCard = (CreditCard) unmarshaller.unmarshal(reader);
    assertEquals("12345678", creditCard.getNumber());
    assertEquals("10/14", creditCard.getExpiryDate());
    assertEquals((Object) 566, creditCard.getControlNumber());
    assertEquals("Visa", creditCard.getType());
  }

}
