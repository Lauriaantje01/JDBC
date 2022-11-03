import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.function.Consumer;

public class manyToManyTests {
    String persistenceUnitName = "jpa-hiber-postgres-pu";
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    EntityManager em = emf.createEntityManager();


    private void executeTransaction(Consumer<EntityManager> consumer) {

        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Something went wrong in the test", e);
        }

    }

    @Test
    void testDelete() {
        executeTransaction(eM -> {
            Title title = em.find(Title.class, 3L);
            Author author = title.getAuthors().iterator().next();
            title.getAuthors().remove(author);
        });
    }

    // waarom wordt de em niet gezien in de testDelete methode?


}
