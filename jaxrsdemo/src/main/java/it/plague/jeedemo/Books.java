package it.plague.jeedemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@XmlRootElement
@XmlSeeAlso(Book.class)
public class Books extends ArrayList<Book> {

  public Books(Collection<? extends Book> c) {
    super(c);
  }

  @XmlElement(name = "book")
  public List<Book> getBooks() {
    return this;
  }

  public void setBooks(List<Book> books) {
    this.addAll(books);
  }

}
