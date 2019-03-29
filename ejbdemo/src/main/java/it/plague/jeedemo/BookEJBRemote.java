package it.plague.jeedemo;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface BookEJBRemote {

  List<Book> findBooks();

  Book createBook(Book book);

  void deleteBook(Book book);

  Book updateBook(Book book);
}
