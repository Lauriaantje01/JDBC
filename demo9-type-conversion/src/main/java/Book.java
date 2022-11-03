import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name = "BookTable")
public class Book {

    @Id
    private Long id;

    private String author;
    private String title;

    @Convert (converter = BooleanYNConverter.class)
    public boolean inPrint;


    public Book(Long id, String author, String title, boolean inPrint) {
        setId(id);
        setAuthor(author);
        setTitle(title);
        this.inPrint = inPrint;
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
