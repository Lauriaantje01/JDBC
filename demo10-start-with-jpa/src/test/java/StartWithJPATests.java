import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StartWithJPATests {
    @Test
    void persistBookWithoutAtGeneratedValue() {
        String persistenceUnitName = "jpa-hiber-postgres-pu";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(new Book(1L, "John", "Surviving in the Java Logging Hell"));
        em.persist(new Book(2L, "Peter", "I wrote a book"));
        tx.commit();
    }

    @Test
    @DisplayName("Jpql query where you select the title of the books")
    void findBooks(){
        String persistenceUnitName = "jpa-hiber-postgres-pu";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager em = emf.createEntityManager();

        addFavouriteBooks(em);

        var numberOfBooksSaved = 3;
        String queryString = "SELECT b FROM Book b";
        var jpqlQuery= em.createQuery(queryString, Book.class);
        var books = jpqlQuery.getResultList();
        var numberOfBooksReceived = books.size();
        System.out.println(books);

        assertThat(numberOfBooksReceived).isEqualTo(numberOfBooksSaved);

    }
    @Test
    @DisplayName("Jpql query where you select only your number 1 favourite book")
    void findFavouriteBooks(){
        String persistenceUnitName = "jpa-hiber-postgres-pu";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager em = emf.createEntityManager();

        addFavouriteBooks(em);

        String queryString = "SELECT b FROM Book b WHERE b.author = 'Tonke Dragt'";
        TypedQuery<Book> jpqlQuery= em.createQuery(queryString, Book.class);
        Book book = jpqlQuery.getSingleResult();
        System.out.println("Favourite book is: " + book.getAuthor() + " " + book.getTitle());

    }

    @Test
    @DisplayName("Jpql query testjes")
    void jpqlTestjes() {
        String persistenceUnitName = "jpa-hiber-postgres-pu";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager em = emf.createEntityManager();

        addFavouriteBooks(em);

        String queryString = "SELECT b FROM Book b WHERE b.author like '%a%'";
        var jpqlQuery = em.createQuery(queryString, Book.class);
        var books = jpqlQuery.getResultList();
        System.out.println("Books with author name containing a: ");
        for (Book b : books) {
            System.out.println(b.getTitle() + " by " + b.getAuthor());
        }

        String queryString2 = "SELECT b FROM Book b WHERE length(b.title) <10";
        var jpqlQuery2 = em.createQuery(queryString2, Book.class);
        var books2 = jpqlQuery2.getResultList();
        System.out.println("Books with titles less than 10 characters: ");
        for (Book b : books2) {
            System.out.println(b.getTitle() + " by " + b.getAuthor());
        }
    }

    @Test
    void testSelectTitleOnly() {
        String persistenceUnitName = "jpa-hiber-postgres-pu";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager em = emf.createEntityManager();

        addFavouriteBooks(em);
        String queryString3 = "SELECT b.title FROM Book b";
        TypedQuery<String> jpqlQuery3= em.createQuery(queryString3, String.class);
        var books3 = jpqlQuery3.getResultList();
        System.out.println("Book titles:");
        for (String title : books3) {
            System.out.println(title);
        }

    }


    @DisplayName("A helper method to persist 3 favourite books")
    public void addFavouriteBooks(EntityManager em){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(new Book(1L, "Tonke Dragt", "Zeeen van Tijd"));
        em.persist(new Book(2L, "Murakami", "Norwegian Wood"));
        em.persist(new Book(3L, "Heller", "Catch 22"));
        tx.commit();
    }
}

