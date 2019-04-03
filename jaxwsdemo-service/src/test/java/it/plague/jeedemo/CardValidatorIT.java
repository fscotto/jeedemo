package it.plague.jeedemo;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CardValidatorIT {

  private static Endpoint endpoint;

  private static Validator cardValidator;

  @BeforeClass
  public static void setUp() throws MalformedURLException {
    final String url = "http://localhost:8081/cardValidator";

    // Publishes the SOAP Web Service
    endpoint = Endpoint.publish(url, new CardValidator());

    // Needed properties to access the web service
    URL wsdlDocumentLocation = new URL(url + "?wsdl");
    String namespaceURI = "http://jeedemo.plague.it/";
    String servicePart = "CardValidatorService";
    String portName = "CardValidatorPort";
    QName serviceQn = new QName(namespaceURI, servicePart);
    QName portQn = new QName(namespaceURI, portName);

    // Creates a service instance
    Service service = Service.create(wsdlDocumentLocation, serviceQn);
    cardValidator = service.getPort(portQn, Validator.class);
  }

  @AfterClass
  public static void tearDown() {
    if (endpoint != null) {
      endpoint.stop();
    }
  }

  @Test
  public void shouldBePublished() {
    assertTrue(endpoint.isPublished());
    assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http", endpoint.getBinding().getBindingID());
  }

  @Test
  public void shouldCheckCreditCardValidity() {
    CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");
    assertTrue("Credit card should be valid", cardValidator.validate(creditCard));

    creditCard.setNumber("12341233");
    assertFalse("Credit card should not be valid", cardValidator.validate(creditCard));
  }

}
