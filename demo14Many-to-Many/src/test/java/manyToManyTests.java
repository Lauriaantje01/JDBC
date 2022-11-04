import org.junit.jupiter.api.DisplayName;
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
    void testDataToevoegen() {
        Title title1 = new Title("Zeeen van Tijd");
        Title title2 = new Title("Norwegian Wood");
        Title title3 =new Title("Catch 22");
        Author author1 = new Author("Tonke Dragt");
        Author author2 = new Author("Murakami");
        Author author3 = new Author("Heller");

        title1.addAuthor(author1);
        title2.addAuthor(author2);
        title3.addAuthor(author3);

        executeTransaction(em -> {


            em.persist(author1);
            em.persist(author2);
            em.persist(author3);
            em.persist(title1);
            em.persist(title2);
            em.persist(title3);
//            Title title = em.find(Title.class, 3L);
//            Author author = title.getAuthors().iterator().next();
//            title.getAuthors().remove(author);
        });
    }

    // waarom wordt de em niet gezien in de testDelete methode?


}
