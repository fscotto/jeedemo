package it.plague.jeedemo;

import java.util.List;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
@Stateless
@DataSourceDefinition(
    name = "java:global/jdbc/lab11DS",
    className = "org.apache.derby.jdbc.EmbeddedDriver",
    url = "jdbc:derby:memory:lab11DB;create=true;user=app;password=app"
)
public class BookEJB {

  @Inject
  private EntityManager em;

  public Book createBook(Book book) {
    em.persist(book);
    return book;
  }

  public List<Book> findAllBooks() {
    return em.createNamedQuery(Book.FIND_ALL, Book.class).getResultList();
  }

  public Book findBookById(Long id) {
    return em.find(Book.class, id);
  }

}
