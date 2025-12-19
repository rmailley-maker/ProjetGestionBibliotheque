package livres;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name="lecteur")
public class Lecteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="num_le")
    private int numero = -1;

    @Column(name="nom_le")
    private String nom;


    @OneToMany(mappedBy="lecteur")
    private List<Emprunt> listeEmprunts = new ArrayList();

    public Lecteur(String nom) {
        this.nom = nom;
    }

    public Lecteur() {

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setListeEmprunts(ArrayList<Emprunt> listeEmprunts) {
        this.listeEmprunts = listeEmprunts;
    }

    public Emprunt emprunte(Livre livre) {
        LocalDate dateDebut = LocalDate.now();
        Emprunt emprunt = new Emprunt(dateDebut, livre, this, dateDebut.plusDays(30));
        this.listeEmprunts.add(emprunt);
        return emprunt;
    }

    public Emprunt rend(Livre livre) {
        Emprunt emprunt = getEmprunt(livre);
        if(emprunt != null) {
            emprunt.setDate_fin(LocalDate.now());
        }
        return emprunt;
    }

    public Emprunt getEmprunt(Livre livre) {
        for(Emprunt emprunt : listeEmprunts) {
            if(emprunt.getLivre().equals(livre)) {
                return emprunt;
            }
        }
        return null;
    }
    public Emprunt getEmprunt(int indice) {
        return this.listeEmprunts.get(indice);
    }
    public List<Emprunt> getEmprunts() {
        return listeEmprunts;
    }
    public void removeEmprunt(Emprunt emprunt) {
        this.listeEmprunts.remove(emprunt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecteur lecteur = (Lecteur) o;
        return numero == lecteur.numero && Objects.equals(nom, lecteur.nom) && Objects.equals(listeEmprunts, lecteur.listeEmprunts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, nom, listeEmprunts);
    }

    @Override
    public String toString() {
        return "Lecteur{" + "numero=" + numero + ", nom='" + nom + '\'' + ", listeEmprunts=" + listeEmprunts + '}';
    }
}
