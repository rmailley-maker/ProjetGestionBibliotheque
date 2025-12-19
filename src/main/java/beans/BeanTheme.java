package beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import livres.Theme;
import persistance.jpa.DAOThemeJPA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("beanTheme")
@ApplicationScoped
public class BeanTheme implements Serializable {
    public List<Theme> getTousLesThemes() {
        return DAOThemeJPA.getInstance().read("");
        /*ArrayList<Theme> liste = new ArrayList<Theme>();
        liste.add(new Theme(1, "Fantastique"));
        return liste;*/
    }
}
