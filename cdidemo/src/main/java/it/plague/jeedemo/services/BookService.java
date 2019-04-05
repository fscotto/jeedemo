package it.plague.jeedemo.services;

import it.plague.jeedemo.annotations.Loggable;
import it.plague.jeedemo.annotations.ThirteenDigits;
import it.plague.jeedemo.beans.Book;
import it.plague.jeedemo.beans.NumberGenerator;
import javax.inject.Inject;

@Loggable
public class BookService {

  @Inject
  @ThirteenDigits
  private NumberGenerator numberGenerator;

  public Book createBook(String title, Float price, String description) {
    Book book = new Book(title, price, description);
    book.setNumber(numberGenerator.generateNumber());
    return book;
  }
}
