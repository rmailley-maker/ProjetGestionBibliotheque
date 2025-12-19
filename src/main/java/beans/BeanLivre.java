package beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import livres.Livre;
import livres.Theme;
import persistance.DAOTheme;
import persistance.jpa.DAOConnectionJPA;
import persistance.jpa.DAOLivreJPA;
import persistance.jpa.DAOThemeJPA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("beanLivre")
@ApplicationScoped
public class BeanLivre implements Serializable {
    private String titre;
    private int numero;
    private int numeroTheme;

    public BeanLivre() {}

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumeroTheme() {
        return numeroTheme;
    }

    public void setNumeroTheme(int numeroTheme) {
        this.numeroTheme = numeroTheme;
    }

    public List<Livre> getTousLesLivres() {
        return DAOLivreJPA.getInstance().read("");
    }

    public String ajouteLivre() {
        try {
            Theme theme = DAOThemeJPA.getInstance().read(numeroTheme);
            Livre livre = new Livre(titre, theme);
            DAOLivreJPA.getInstance().create(livre);
            DAOConnectionJPA.getInstance().commit();
            return "ajout_valide";
        } catch(Exception e) {
            return "ajout_invalide";
        }
    }
}
