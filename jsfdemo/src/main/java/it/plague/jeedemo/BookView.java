package it.plague.jeedemo;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Named
@RequestScoped
@NoArgsConstructor
public class BookView {

  @Inject
  private BookEJB bookEJB;

  @Getter
  @Setter
  private Book book = new Book();

  public void doFindBookById() {
    book = bookEJB.findBookById(book.getId());
  }

  public String doCreateBook() {
    bookEJB.createBook(book);
    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO, "Book created",
            "The book " + book.getTitle() + " has been created with id = " + book.getId()));
    return "newBook.xhtml";
  }

}
