package it.plague.jeedemo;

import java.util.Date;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class OrderProducer {

  public static void main(String[] args) throws NamingException {
    // Creates an orderDto with a total amount parameter
    Float totalAmount = Float.valueOf(args[0]);
    OrderDTO order = new OrderDTO(12342L, new Date(), "Betty Moreu", totalAmount);

    // Get the JNDI context
    Context jndiContext = new InitialContext();

    // Looks up the administered objects
    ConnectionFactory connectionFactory =
        (ConnectionFactory) jndiContext.lookup("jms/javaee7/ConnectionFactory");
    Destination destination =
        (Destination) jndiContext.lookup("jms/javaee7/Topic");

    try (JMSContext context = connectionFactory.createContext()) {
      // Sends an object message to the topic
      context.createProducer().setProperty("orderAmount", totalAmount).send(destination, order);
    }
  }
}
