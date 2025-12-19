package tests;

import livres.Theme;
import org.junit.Test;
import persistance.DAOException;
import persistance.DAOTheme;
import persistance.jpa.DAOConnectionJPA;
import persistance.jpa.DAOThemeJPA;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;


public class TestDAOThemeJPA {
    @Test
    public void ajouterEtSupprimerTheme() {
        // On s'assure que la base est vide
        DAOConnectionJPA.getInstance().viderBase();
        DAOTheme dao = DAOThemeJPA.getInstance();
        assertEquals(0, dao.getNombreThemes());

        // On crée 3 objets métier
        Theme   policier = new Theme("Policier"),
                fantastitique = new Theme("Fantastique"),
                sf = new Theme("Science fiction");

        // On les ajoute un par un en s'assurant que la base de données évolue correctement
        // et que les numéros de thème sont bien mis à jour dans les objets métier
        dao.create(policier);
        assertEquals(1, dao.getNombreThemes());
        assertEquals(1, policier.getNumero());

        dao.create(fantastitique);
        assertEquals(2, fantastitique.getNumero());

        dao.create(sf);
        assertEquals(3, sf  .getNumero());
        assertEquals(3, dao.getNombreThemes());

        // Quelques lectures dans la base
        // On les lit tous
        List<Theme> listeThemes = dao.read("");
        assertEquals(3, listeThemes.size());

        // On en lit un à partir de son numéro
        Theme fant = dao.read(2);
        assertEquals(fantastitique, fant);

        // On en lit un à partir du début de son libellé
        listeThemes = dao.read("Fant");
        assertEquals(1, listeThemes.size());
        assertEquals(fantastitique, listeThemes.getFirst());

        // On en détruit un et on vérifie qu'il n'existe plus
        dao.delete(fantastitique);
        assertFalse(DAOConnectionJPA.getInstance().getEntityManager().contains(fantastitique));
        assertEquals(2, dao.getNombreThemes());
        listeThemes = dao.read("Fant");
        assertEquals(0, listeThemes.size());

        // On vérifie que la contrainte d'unicité est bien prise en compte
        assertThrows(DAOException.class, () -> dao.create(new Theme("Policier")));
    }


}
