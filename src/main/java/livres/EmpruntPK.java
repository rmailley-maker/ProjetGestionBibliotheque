package livres;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class EmpruntPK implements Serializable {
    private int num_le;
    private int num_li;
    private LocalDate date_debut;

    public int getNum_le() {
        return num_le;
    }

    public void setNum_le(int num_le) {
        this.num_le = num_le;
    }

    public int getNum_li() {
        return num_li;
    }

    public void setNum_li(int num_li) {
        this.num_li = num_li;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpruntPK empruntPK = (EmpruntPK) o;
        return num_le == empruntPK.num_le && num_li == empruntPK.num_li && Objects.equals(date_debut, empruntPK.date_debut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num_le, num_li, date_debut);
    }

    @Override
    public String toString() {
        return "EmpruntPK{" + "num_le=" + num_le + ", num_li=" + num_li + ", date_debut=" + date_debut + '}';
    }
}
