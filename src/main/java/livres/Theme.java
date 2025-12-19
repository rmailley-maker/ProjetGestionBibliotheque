package livres;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name="theme")
public class Theme {

    public static final int NON_PERSISTANT = -1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="num_th")
    private int numero = NON_PERSISTANT;

    @Column(name="lib_th")
    private String libelle;

    public Theme(String libelle) {
        this.libelle = libelle;
    }
    public Theme(int numero, String libelle) {
        this.numero = numero;
        this.libelle = libelle;
    }

    public Theme() {}

    public int getNumero() {
        return numero;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme = (Theme) o;
        return numero == theme.numero && Objects.equals(libelle, theme.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, libelle);
    }

    @Override
    public String toString() {
        return "Theme{" + "numero=" + numero + ", libelle='" + libelle + '\'' + '}';
    }
}
