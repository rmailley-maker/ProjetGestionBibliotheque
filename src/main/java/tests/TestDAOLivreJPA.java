package tests;
import org.junit.Test;
import jakarta.persistence.EntityManager;
import livres.Livre;
import livres.Theme;
import persistance.DAOException;
import persistance.DAOLivre;
import persistance.DAOTheme;
import persistance.jpa.DAOConnectionJPA;
import persistance.jpa.DAOLivreJPA;
import persistance.jpa.DAOThemeJPA;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestDAOLivreJPA {
    @Test
    public void testLivre() throws DAOException {
        DAOConnectionJPA.getInstance().viderBase();
        DAOTheme daoTheme = DAOThemeJPA.getInstance();
        DAOLivre daoLivre = DAOLivreJPA.getInstance();

        assertEquals(0, daoTheme.getNombreThemes());
        assertEquals(0, daoLivre.getNombreLivres());


        Theme fantastique =  new Theme("Fantastique");
        Livre bilbo = new Livre("Bilbo le hobbit", fantastique);

        daoLivre.create(bilbo);

        assertEquals(1, daoTheme.getNombreThemes());
        assertEquals(1, daoLivre.getNombreLivres());

        Livre anneaux = new Livre("Le seigneur des anneaux", fantastique);
        daoLivre.create(anneaux);

        assertEquals(1, daoTheme.getNombreThemes());
        assertEquals(2, daoLivre.getNombreLivres());

        assertEquals(2, daoLivre.read("").size());

        daoLivre.delete(bilbo);
        assertEquals(1, daoTheme.getNombreThemes());
        assertEquals(1, daoLivre.getNombreLivres());
        assertNull(daoLivre.read(1));
        DAOConnectionJPA.getInstance().commit();
    }
}

