package org.example;

import org.junit.jupiter.api.Test;

public class EntityPractice {
    String pu = "jpa-hibernate-postgres-persistence-unit";


    @Test
    void test(){
        EntityManagerFactory emf = Persistence.CreateEntityManagerFactory(pu);
        EntityManager em = emf.createEntityManager();
    }
}
