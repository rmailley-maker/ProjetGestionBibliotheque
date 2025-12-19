package livres;

import jakarta.persistence.*;
import org.hibernate.boot.jaxb.internal.stax.LocalXmlResourceResolver;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="emprunt")
public class Emprunt {

    @EmbeddedId
    private EmpruntPK id;

    @Column
    private LocalDate date_fin;


    @MapsId("num_li")
    @ManyToOne
    @JoinColumn(name="num_li")
    private Livre livre;

    @MapsId("num_le")
    @ManyToOne
    @JoinColumn(name="num_le")
    private Lecteur lecteur;

    public Emprunt(LocalDate date_debut, Livre livre, Lecteur lecteur, LocalDate date_fin) {
        this.id = new EmpruntPK();
        this.id.setDate_debut(date_debut);
        this.date_fin = date_fin;
        this.livre = livre;
        this.lecteur = lecteur;
    }

    public Emprunt() {}

    public LocalDate getDate_debut() {
        return id.getDate_debut();
    }

    public void setDate_debut(LocalDate date_debut) {
        this.id.setDate_debut(date_debut);
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Lecteur getLecteur() {
        return lecteur;
    }

    public void setLecteur(Lecteur lecteur) {
        this.lecteur = lecteur;
    }

    public EmpruntPK getId() {
        return id;
    }

    public void setId(EmpruntPK id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprunt emprunt = (Emprunt) o;
        return Objects.equals(id, emprunt.id) && Objects.equals(date_fin, emprunt.date_fin) && Objects.equals(livre, emprunt.livre) && Objects.equals(lecteur, emprunt.lecteur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date_fin, livre, lecteur);
    }

    @Override
    public String toString() {
        return "Emprunt{" + "id=" + id + ", date_fin=" + date_fin + ", livre=" + livre + ", lecteur=" + lecteur + '}';
    }
}
