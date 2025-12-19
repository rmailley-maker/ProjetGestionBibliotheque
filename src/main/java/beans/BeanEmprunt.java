package beans;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.RepeatedFieldBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import livres.Emprunt;
import livres.Lecteur;
import livres.Livre;
import persistance.jpa.DAOConnectionJPA;
import persistance.jpa.DAOLecteurJPA;
import persistance.jpa.DAOLivreJPA;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named("beanEmprunt")
@ApplicationScoped
public class BeanEmprunt implements Serializable {
    private int numeroLecteur;
    private int numeroLivre;

    //private Livre livreARendre;

    public BeanEmprunt() {
    }

    /*public Livre getLivreARendre() {
        return livreARendre;
    }

    public void setLivreARendre(Livre livreARendre) {
        this.livreARendre = livreARendre;
    }*/

    public int getNumeroLivre() {
        return numeroLivre;
    }

    public void setNumeroLivre(int numeroLivre) {
        this.numeroLivre = numeroLivre;
    }

    public int getNumeroLecteur() {
        return numeroLecteur;
    }

    public void setNumeroLecteur(int numeroLecteur) {
        this.numeroLecteur = numeroLecteur;
    }

    public String emprunte() {
        Lecteur lecteur = new Lecteur();
        Livre livre = new Livre();

        try {
            lecteur = DAOLecteurJPA.getInstance().read(numeroLecteur);
            if(lecteur == null) {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le numero de lecteur n'existe pas."));
                 return null;
            }
        }catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le numero de lecteur n'existe pas."));
            return null;
        }

        try {
            livre = DAOLivreJPA.getInstance().read(numeroLivre);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le numero de livre n'existe pas."));
            return null;
        }

        try {
            LocalDate today = LocalDate.now();
            LocalDate dateRendue = today.plusDays(1);
            Emprunt emprunt = new Emprunt(today, livre, lecteur, dateRendue);

            DAOLecteurJPA.getInstance().add(emprunt);
            DAOConnectionJPA.getInstance().commit();
        } catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur dans l'insertion."));
            return null;
        }
        return null;
    }

    public String voirEmprunts() {
        Lecteur lecteur = DAOLecteurJPA.getInstance().read(numeroLecteur);
        if(lecteur == null) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le numero de lecteur n'existe pas."));
             return null;
        }
        return "liste_emprunt";
    }

    public String getNomLecteur() {
        Lecteur lecteur = DAOLecteurJPA.getInstance().read(numeroLecteur);
        return lecteur.getNom();
    }

    public List<Emprunt> getTousLesEmprunts() {
        Lecteur lecteur = DAOLecteurJPA.getInstance().read(numeroLecteur);
        return lecteur.getEmprunts();
    }

    public void rendre(Emprunt emprunt) {
        DAOLecteurJPA.getInstance().delete(emprunt);
        DAOConnectionJPA.getInstance().commit();
    }
}
