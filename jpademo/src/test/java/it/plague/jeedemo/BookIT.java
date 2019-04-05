package it.plague.jeedemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BookIT {

  private EntityManager em;

  @Before
  public void initEntityManager() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpademoTestUnit");
    em = emf.createEntityManager();
  }

  @After
  public void closeEntityManager() {
    if (em != null) {
      em.close();
    }
  }

  @Test
  public void shouldCreateJavaEE7Book() {
    Book book = em.find(Book.class, 1001L);
    assertEquals("Beginning Java EE 7", book.getTitle());
  }

  @Test
  public void shouldCreateH2G2Book() {
    Book book = Book.builder()
        .title("H2G2")
        .description("The Hitchhiker's Guide to the Galaxy")
        .price(12.5f)
        .isbn("1-84023-742-2")
        .nbOfPage(354)
        .illustrations(false)
        .build();

    em.getTransaction().begin();
    em.persist(book);
    em.getTransaction().commit();
    assertNotNull("ID should not be null", book.getId());

    book = em.createNamedQuery("Book.findOneH2G2", Book.class).getSingleResult();
    assertEquals("The Hitchhiker's Guide to the Galaxy", book.getDescription());
  }

  @Test(expected = ConstraintViolationException.class)
  public void shouldRaiseConstraintViolationCauseNullTitle() {
    Book book = Book.builder()
        .title(null)
        .description("Null title, should fail")
        .price(12.5f)
        .isbn("1-84023-742-2")
        .nbOfPage(354)
        .illustrations(false)
        .build();
    em.persist(book);
  }
}
