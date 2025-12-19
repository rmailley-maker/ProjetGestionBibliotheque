package persistance.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import persistance.DAOException;

public class DAOConnectionJPA {
    private static DAOConnectionJPA instance = null;

    public static DAOConnectionJPA getInstance() throws DAOException {
        if (instance == null) {
            instance = new DAOConnectionJPA();
        }

        return instance;
    }

    private EntityManager em;//= Persistence.createEntityManagerFactory("MesFilms").createEntityManager();
    private EntityTransaction tx; // = entityManager.getTransaction();

    private DAOConnectionJPA() {
        em = Persistence.createEntityManagerFactory("librairie").createEntityManager();
        tx = em.getTransaction();
        tx.begin();
    }

    public EntityManager getEntityManager() {
        if (! tx.isActive())
            tx.begin();

        return em;
    }


    public void commit() {
        if (tx.isActive()) {
            tx.commit();
            em.clear();
        }
    }

    public void rollback() {
        if (tx.isActive())
            tx.rollback();
    }

    public void closeConnection() {
        if (tx.isActive())
            tx.commit();
        em.close();
        instance = null;
    }

    public void viderBase() {
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE theme").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE livre").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE lecteur").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE emprunt").executeUpdate();
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS=1").executeUpdate();
        instance.commit();
    }

    public boolean estAttache(Object object) {
        return em.contains(object);
    }
}
