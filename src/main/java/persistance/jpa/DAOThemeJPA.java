package persistance.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import livres.Theme;
import persistance.DAOException;
import persistance.DAOTheme;

import java.util.List;

public class DAOThemeJPA implements DAOTheme {
    private static DAOTheme instance = null;

    private DAOThemeJPA() {}

    public static DAOTheme getInstance() {
        if(instance == null) {
            instance = new DAOThemeJPA();
        }
        return instance;
    }
    @Override
    public void create(Theme theme) throws DAOException {
        try {
            EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();
            em.persist(theme);
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public Theme read(int id) throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();
        Theme theme = em.find(Theme.class, id);
        if(theme == null) {
            throw new DAOException("Theme not found");
        }
        return theme;
    }

    @Override
    public List<Theme> read(String libelle) throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();

        TypedQuery<Theme> q = em.createQuery("SELECT t FROM Theme t WHERE libelle LIKE :libelleT", Theme.class);
        q.setParameter("libelleT", "%" + libelle + "%");

        return q.getResultList();
    }

    @Override
    public void delete(Theme theme) throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();
        if(!em.contains(theme)) {
            throw new DAOException("Le theme n'est pas manage. Suppression impossible.");
        }
        em.remove(theme);
    }

    @Override
    public int getNombreThemes() throws DAOException {
        EntityManager em = DAOConnectionJPA.getInstance().getEntityManager();

        Query q = em.createQuery("SELECT COUNT(*) FROM Theme t");

        Long nb = (Long) q.getSingleResult();

        return nb.intValue();
    }
}
