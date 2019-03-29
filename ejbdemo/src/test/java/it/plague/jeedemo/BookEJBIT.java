package it.plague.jeedemo;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import org.junit.Test;

public class BookEJBIT {

  @Test
  public void shouldCreateABook() throws Exception {
    Map<String, Object> properties = new HashMap<>();
    properties.put(EJBContainer.MODULES, new File("target/classes"));
    try (EJBContainer ec = EJBContainer.createEJBContainer(properties)) {
      Context ctx = ec.getContext();

      // Check JNDI dependencies (Datasource and EJBs)
      assertNotNull(ctx.lookup("java:global/jdbc/ejbdemoDS"));
      assertNotNull(ctx.lookup("java:global/classes/BookEJB!it.plague.jeedemo.BookEJBRemote"));
      assertNotNull(ctx.lookup("java:global/classes/BookEJB!it.plague.jeedemo.BookEJB"));

      // Looks up the EJB
      BookEJB bookEJB = (BookEJB) ctx.lookup("java:global/classes/BookEJB!it.plague.jeedemo.BookEJB");

      // Find all the books and makes sure there are two (inserted by the DBPopulator)
      assertEquals(2, bookEJB.findBooks().size());

      // Creates an instance of book;
      Book book = Book.builder()
          .title("H2G2")
          .price(12.5f)
          .description("Scifi book")
          .isbn("1-24561-799-0")
          .nbOfPage(354)
          .illustrations(false)
          .build();
      book = bookEJB.createBook(book);
      assertNotNull("ID should not be null", book.getId());

      // Find all the books and makes sure is an extra one
      assertEquals(3, bookEJB.findBooks().size());

      // Removes the created book
      bookEJB.deleteBook(book);

      // Finds all the books and makes sure there is one less
      assertEquals(2, bookEJB.findBooks().size());
    }
  }
}
