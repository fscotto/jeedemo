package it.plague.jeedemo;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class OrderConsumer {

  public static void main(String[] args) throws NamingException {
    // Get the JNDI context
    Context jndiContext = new InitialContext();

    // Looks up the administered objects
    ConnectionFactory connectionFactory =
        (ConnectionFactory) jndiContext.lookup("jms/javaee7/ConnectionFactory");
    Destination destination =
        (Destination) jndiContext.lookup("jms/javaee7/Topic");

    try (JMSContext context = connectionFactory.createContext()) {
      while (true) {
        OrderDTO order = context.createConsumer(destination).receiveBody(OrderDTO.class);
        System.out.println("Order received: " + order);
      }
    }
  }
}
