package it.plague.jeedemo;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

@Stateless
@LocalBean
public class BookEJB implements BookEJBRemote {

  @Inject
  private EntityManager em;

  @Override
  public List<Book> findBooks() {
    TypedQuery<Book> query = em.createNamedQuery("Book.findAll", Book.class);
    return query.getResultList();
  }

  @Override
  public @NotNull Book createBook(@NotNull Book book) {
    em.persist(book);
    return book;
  }

  @Override
  public @NotNull Book updateBook(@NotNull Book book) {
    return em.merge(book);
  }

  @Override
  public void deleteBook(@NotNull Book book) {
    em.remove(em.merge(book));
  }
}
