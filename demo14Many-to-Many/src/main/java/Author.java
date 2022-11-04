import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Set<Title> titles = new HashSet<>();

    public Author(String name) {
        setName(name);
    }

    public Author() {
        setId(null);
        setName("Unknown");
    }

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
