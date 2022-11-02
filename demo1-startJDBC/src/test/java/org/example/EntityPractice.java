package org.example;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityPractice {


    @Test
    void test(){
        String pu = "jpa-hibernate-postgres-persistence-unit";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(pu);
        EntityManager em = emf.createEntityManager();
    }
}
