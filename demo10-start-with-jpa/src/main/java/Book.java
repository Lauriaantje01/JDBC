import javax.persistence.*;

@Entity
public class Book {

    @Id
    private Long id;

    private String author;
    private String title;


    public Book(Long id, String author, String title) {
        setId(id);
        setAuthor(author);
        setTitle(title);
    }

    public Book(){
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
