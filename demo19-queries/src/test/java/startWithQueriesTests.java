
import demo50startwithqueries.app.domain.mappingalltables.Author;
import demo50startwithqueries.app.domain.mappingalltables.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.*;

import javax.persistence.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class startWithQueriesTests {

    String persistenceUnitName = "jpa-hiber-postgres-pu";
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    EntityManager em = emf.createEntityManager();

    private static final Logger logger = LoggerFactory.getLogger(logTest.class);

    @Test
    @DisplayName("Then select all authors that have a contract 1.0 and assertThat() there are 19 of them.")
    private void typedQuery() {

        String jpqlAllAuthors = "SELECT a FROM Author a";
        TypedQuery<Author> jpqlQueryAllAuthors = em.createQuery(jpqlAllAuthors, Author.class);
        List<Author> authors = jpqlQueryAllAuthors.getResultList();

        List<String> authorsLog = new ArrayList<>();
        for (Author a : authors) {
            authorsLog.add(a.toString());
        }

        for (String s : authorsLog) {
            System.out.println(s);
        }
        assertThat(authors.size()).isEqualTo(23);
    }

    @Test
    @DisplayName("Then select all authors that have a contract 1.0 and assertThat() there are 19 of them.")
    void typedQuery2() {

        String jpqlAllAuthors = "SELECT a FROM Author a WHERE a.contract = 1.0";
        TypedQuery<Author> jpqlQueryAllAuthors = em.createQuery(jpqlAllAuthors, Author.class);
        List<Author> authors = jpqlQueryAllAuthors.getResultList();

        assertThat(authors.size()).isEqualTo(19);
    }

    @Test
    @DisplayName("Select first and last name.")
    void typedQuery3() {

        String jpqlAllAuthors = "SELECT a.firstname, a.lastname FROM Author a";
        var jpqlQueryAllAuthors = em.createQuery(jpqlAllAuthors, Object[].class);
        List<Object[]> authors = (List<Object[]>) jpqlQueryAllAuthors.getResultList();

        for (Object[] object : authors) {
            for (Object string : object){
                System.out.println(string);
            }
        }
        assertThat(authors.get(0)[0]).isEqualTo("Johnson");
        assertThat(authors.get(0)[1]).isEqualTo("White");
    }

    @Test
    void persistBookWithoutGeneratedValue() {
        String jpqlString = "SELECT t FROM Title t where t.title LIKE 'Life Without Fear'";
        var jpqlQueryBook = em.createQuery(jpqlString, Title.class);
        Title book = jpqlQueryBook.getSingleResult();

        logger.info("Start persist book");
            em.persist(book);
        logger.info("End persist book");

    }
}
