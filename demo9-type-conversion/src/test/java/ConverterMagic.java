import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class ConverterMagic {

    @Test
    void setUpDataAndFindData() {
        String persistenceUnitName = "jpa-hiber-postgres-pu";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager em = emf.createEntityManager();

        createTableWithBooks(em);
        String queryString = "select b from BookTable b";
        TypedQuery<Book> jpqlQuery= em.createQuery(queryString, Book.class);
        List<Book> books = jpqlQuery.getResultList();
        for (Book b : books) {
            System.out.println(b.getTitle() + " is in print: " + b.inPrint);
        }

        Query sqlNativeQuery = em.createNativeQuery("select inPrint FROM BookTable");
        // voor de native query wordt in sql de String query meegegeven ipv JPQL)
        List<String> inPrints = sqlNativeQuery.getResultList();
        for(String s : inPrints) {
           System.out.println(s);
        }

        System.out.println(em.find(Book.class, 1L));

        assertThat(inPrints.get(0)).isEqualTo("no");

    }

    private void createTableWithBooks(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(new Book(1L, "Tonke Dragt", "Zeeen van Tijd", false));
        em.persist(new Book(2L, "Tonke Dragt", "Torens van Februari", false));
        em.persist(new Book(3L, "Tonke Dragt", "De tijd zal het leren", true));
        em.persist(new Book(4L, "Tonke Dragt", "De Zevensprong", true));
        tx.commit();
    }
}
