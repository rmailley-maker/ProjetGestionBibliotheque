package persistance;

import livres.Emprunt;
import livres.Lecteur;

public interface DAOLecteur {
    void create(Lecteur lecteur) throws DAOException;
    Lecteur read(int id) throws DAOException;
    int getNombreLecteur() throws DAOException;
    void add(Emprunt emprunt) throws DAOException;
    void update(Emprunt emprunt) throws DAOException;
}
