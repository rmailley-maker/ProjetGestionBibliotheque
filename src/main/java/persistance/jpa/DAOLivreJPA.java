package persistance.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import livres.Livre;
import persistance.DAOException;
import persistance.DAOLivre;
import persistance.jpa.DAOConnectionJPA;

import java.util.List;

public class DAOLivreJPA implements DAOLivre {
    private static DAOLivreJPA instance = null;

    public DAOLivreJPA() {}

    static public DAOLivreJPA getInstance() {
        if(instance == null) {
            instance = new DAOLivreJPA();
        }
        return instance;
    }

    @Override
    public int getNombreLivres() throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();

        long r = (long)em.createQuery("select count(*) from Livre").getSingleResult();

        return (int)r;
    }

    @Override
    public void create(Livre bilbo) throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();

        em.persist(bilbo);
    }

    @Override
    public List<Livre> read(String titre) throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();

        TypedQuery<Livre> q = em.createQuery("select l from Livre l where titre LIKE :titreL", Livre.class);

        q.setParameter("titreL", "%" + titre+ "%");

        return q.getResultList();
    }

    public Livre read(int id) throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();
        return em.find(Livre.class, id);
    }

    public void delete(Livre livre) throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();
        if(!em.contains(livre)) {
            throw new DAOException("Le theme n'est pas manage. Suppression impossible.");
        }
        em.remove(livre);
    }
}
