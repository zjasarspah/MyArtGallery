package ba.unsa.etf.rpr.controllers.model;

import ba.unsa.etf.rpr.domain.Artist;
import javafx.beans.property.SimpleStringProperty;

public class ArtistModel {

    public SimpleStringProperty name = new SimpleStringProperty("");
    public SimpleStringProperty  lifespan = new SimpleStringProperty("");

    public void fromArtist(Artist a){
        this.name.set(a.getName());
        this.lifespan.set(a.getLifespan());
    }

    public Artist toArtist(){
        Artist a = new Artist();
        a.setName(this.name.getValue());
        a.setLifespan(this.lifespan.getValue());
        return a;
    }
}
