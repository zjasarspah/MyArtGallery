package ba.unsa.etf.rpr.controllers.model;

import ba.unsa.etf.rpr.domain.Artist;
import javafx.beans.property.SimpleStringProperty;

/**
 * Model for Artist
 * @author Zerina Jasarspahic
 */

public class ArtistModel {

    public SimpleStringProperty name = new SimpleStringProperty("");
    public SimpleStringProperty  lifespan = new SimpleStringProperty("");

    /**
     * Public method for parsing Artist's fields into Properties
     * @author Zerina Jasarspahic
     */
    public void fromArtist(Artist a){
        this.name.set(a.getName());
        this.lifespan.set(a.getLifespan());
    }

    /**
     * Public method for parsing Properties into Artist's fields
     * @author Zerina Jasarspahic
     */

    public Artist toArtist(){
        Artist a = new Artist();
        a.setName(this.name.getValue());
        a.setLifespan(this.lifespan.getValue());
        return a;
    }
}
