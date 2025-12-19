package tests.jdbc;

import livres.Emprunt;
import livres.Lecteur;
import livres.Livre;
import livres.Theme;
import org.junit.Test;
import persistance.DAOException;
import persistance.DAOLecteur;
import persistance.DAOLivre;
import persistance.jpa.DAOConnectionJPA;
import persistance.jpa.DAOLecteurJPA;
import persistance.jpa.DAOLivreJPA;

import static org.junit.Assert.assertEquals;

public class TestDAOLecteurJPA {
    @Test
    public void testDAOLecteur() throws DAOException {
        DAOConnectionJPA.getInstance().viderBase();
        DAOLivre daoLivre = DAOLivreJPA.getInstance();
        DAOLecteur daoLecteur = DAOLecteurJPA.getInstance();

        Theme fantastique = new Theme("Fantastique");
        Livre livre = new Livre("Bilbo le hobbit", fantastique);
        daoLivre.create(livre);

        assertEquals(0, daoLecteur.getNombreLecteur());
        Lecteur lecteur = new Lecteur("John DOE");
        daoLecteur.create(lecteur);
        assertEquals(1, daoLecteur.getNombreLecteur());

        Emprunt emprunt = lecteur.emprunte(livre);
        daoLecteur.add(emprunt);

        Emprunt empruntRendu = lecteur.rend(livre);
        if(empruntRendu != null)
            daoLecteur.update(empruntRendu);

        Lecteur l2 = daoLecteur.read(lecteur.getNumero());
        assertEquals(1, l2.getEmprunts().size());


        Livre _2001 = new Livre("2001", new Theme("Science fiction"));
        daoLivre.create(_2001);
        Emprunt emp = lecteur.emprunte(_2001);
        daoLecteur.add(emp);

        l2 = daoLecteur.read(lecteur.getNumero());
        assertEquals(2, l2.getEmprunts().size());

        DAOConnectionJPA.getInstance().commit();

    }
}
