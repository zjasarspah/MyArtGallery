package ba.unsa.etf.rpr.controllers.model;

import ba.unsa.etf.rpr.domain.ArtStyle;
import javafx.beans.property.SimpleStringProperty;

/**
 * Model for Art Style
 * @author Zerina Jasarspahic
 */
public class ArtStyleModel {

    public SimpleStringProperty name = new SimpleStringProperty("");
    public SimpleStringProperty  year = new SimpleStringProperty("");

    /**
     * Public method for parsing Art Style's fields into Properties
     * @author Zerina Jasarspahic
     */
    public void fromArtStyle (ArtStyle a){
        this.name.set(a.getName());
        this.year.set(a.getYear());
    }

    /**
     * Public method for parsing Properties into Art Style's fields
     * @author Zerina Jasarspahic
     */
    public ArtStyle toArtStyle(){
        ArtStyle a = new ArtStyle();
        a.setName(this.name.getValue());
        a.setYear(this.year.getValue());
        return a;
    }
}
