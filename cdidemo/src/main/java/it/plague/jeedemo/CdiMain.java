package it.plague.jeedemo;

import it.plague.jeedemo.beans.Book;
import it.plague.jeedemo.services.BookService;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class CdiMain {

  public static void main(String[] args) {
    Weld weld = new Weld();
    WeldContainer container = weld.initialize();
    BookService bookService = container.instance().select(BookService.class).get();
    Book book = bookService.createBook("H2G2", 12.5f, "Geeky scifi Book");
    System.out.println(book);
    weld.shutdown();
  }
}
