import javax.persistence.*;
import java.util.*;

@Entity
public class Title {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @ManyToMany
    @JoinTable(
            name = "tile_author_mm_uni",
            joinColumns = {@JoinColumn(name="title_id")},
            inverseJoinColumns = {@JoinColumn(name="id_title")}
    )
    private Set<Author> authors = new HashSet<>();



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}