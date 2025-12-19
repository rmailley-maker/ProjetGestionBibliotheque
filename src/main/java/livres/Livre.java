package livres;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "livre")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="num_li")
    private int numero = -1;

    @Column(name="titre_li")
    private String titre;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (name="num_th", referencedColumnName = "num_th")
    private Theme theme;

    public Livre(String titre, Theme theme) {
        this.titre = titre;
        this.theme = theme;
    }

    public Livre(int numero, String titre, Theme theme) {
        this.numero = numero;
        this.titre = titre;
        this.theme = theme;
    }

    public Livre() {}

    public int getNumero() {
        return numero;
    }

    public String getTitre() {
        return titre;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livre livre = (Livre) o;
        return numero == livre.numero && Objects.equals(titre, livre.titre) && Objects.equals(theme, livre.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, titre, theme);
    }

    @Override
    public String toString() {
        return "Livre{" + "numero=" + numero + ", titre='" + titre + '\'' + ", theme=" + theme + '}';
    }
}