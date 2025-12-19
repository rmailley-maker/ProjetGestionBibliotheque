package persistance;

import livres.Theme;

import java.util.List;

public interface DAOTheme {
     void create(Theme theme) throws DAOException;
     Theme read(int id) throws DAOException;
     List<Theme> read(String libelle) throws DAOException;
     void delete(Theme theme) throws DAOException;
     int getNombreThemes() throws DAOException;
}
