package ba.unsa.etf.rpr.controllers.model;

import ba.unsa.etf.rpr.domain.ArtStyle;
import javafx.beans.property.SimpleStringProperty;

/**
 * Model for Art Style
 * @author Zerina Jasarspahic
 */
public class ArtStyleModel {

    public SimpleStringProperty name = new SimpleStringProperty("");
    public SimpleStringProperty  duration = new SimpleStringProperty("");

    /**
     * Public method for parsing Art Style's fields into Properties
     * @author Zerina Jasarspahic
     */
    public void fromArtStyle (ArtStyle a){
        this.name.set(a.getName());
        this.duration.set(a.getDuration());
    }

    /**
     * Public method for parsing Properties into Art Style's fields
     * @author Zerina Jasarspahic
     */
    public ArtStyle toArtStyle(){
        ArtStyle a = new ArtStyle();
        a.setName(this.name.getValue());
        a.setDuration(this.duration.getValue());
        return a;
    }
}
