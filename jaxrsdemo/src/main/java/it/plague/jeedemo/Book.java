package it.plague.jeedemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQuery(name = Book.FIND_ALL, query = "select b from Book b")
public class Book {

  public static final String FIND_ALL = "FIND_ALL";

  @Id
  @GeneratedValue
  private String id;

  @Column(nullable = false)
  private String title;

  private Float price;

  @Column(length = 2000)
  private String description;

  private String isbn;

  private Integer nbOfPage;

  private Boolean illustrations;

}
