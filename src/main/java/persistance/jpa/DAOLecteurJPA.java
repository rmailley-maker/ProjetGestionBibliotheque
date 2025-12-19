package persistance.jpa;

import jakarta.persistence.EntityManager;
import livres.Emprunt;
import livres.Lecteur;
import livres.Livre;
import persistance.DAOException;
import persistance.DAOLecteur;

public class DAOLecteurJPA implements DAOLecteur {
    private static DAOLecteurJPA instance = null;

    public static DAOLecteurJPA getInstance() {
        if(instance == null) {
            instance = new DAOLecteurJPA();
        }
        return instance;
    }

    public DAOLecteurJPA() {}

    @Override
    public void create(Lecteur lecteur) throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();

        em.persist(lecteur);
    }

    @Override
    public Lecteur read(int id) throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();
        return em.find(Lecteur.class, id);
    }

    @Override
    public int getNombreLecteur() throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();

        long r = (long)em.createQuery("select count(*) from Lecteur").getSingleResult();

        return (int)r;
    }

    @Override
    public void add(Emprunt emprunt) throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();
        em.persist(emprunt);
    }

    @Override
    public void update(Emprunt emprunt) throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();
        em.merge(emprunt);

    }

    public void delete(Emprunt emprunt) throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();
        em.remove(emprunt);
    }
}
