import demo50startwithqueries.app.domain.mapping01table.Author;
import org.junit.jupiter.api.*;

import javax.persistence.*;
import java.util.*;

public class startWithQueriesTests {

    String persistenceUnitName = "jpa-hiber-postgres-pu";
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    EntityManager em = emf.createEntityManager();


@Test
@DisplayName("Select all authors and assertThat() there are 23 authors. Log all authors using toString(). " +
        "Then select all authors that have a contract 1.0 and assertThat() there are 19 of them.")
    void typedQuery() {

    String jpqlAllAuthors = "SELECT a FROM Author a";
    TypedQuery<Author> jpqlQueryAllAuthors = em.createQuery(jpqlAllAuthors, Author.class);
    List<Author> authors = jpqlQueryAllAuthors.getResultList();
    for (Author a : authors) {
        System.out.println(a);
    }

}
}
