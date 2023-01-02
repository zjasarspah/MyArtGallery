package ba.unsa.etf.rpr.controllers.model;

import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.ArtWork;
import ba.unsa.etf.rpr.domain.Artist;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Model for Artwork
 * @author Zerina Jasarspahic
 */

public class ArtworkModel {

    public SimpleStringProperty name = new SimpleStringProperty("");
    public SimpleObjectProperty<Artist> artist = new SimpleObjectProperty<>();
    public SimpleObjectProperty<ArtStyle> artStyle = new SimpleObjectProperty<>();
    public SimpleStringProperty artistName = new SimpleStringProperty("");
    public SimpleStringProperty artStyleName = new SimpleStringProperty("");

    /**
     * Public method for parsing Artwork's fields into Properties
     * @author Zerina Jasarspahic
     */
    public void fromArtwork (ArtWork aw){
        this.name.set(aw.getName());
        this.artist.set(aw.getArtist());
        this.artStyle.set(aw.getMovement());
    }

    /**
     * Public method for parsing Artwork's fields into Properties
     * This method uses names (STRING) of the artist and the art style
     * This method is used for labels
     * @author Zerina Jasarspahic
     */
    public void viewFromArtwork (ArtWork aw){
        this.name.set(aw.getName());
        this.artistName.set(aw.getArtist().getName());
        this.artStyleName.set(aw.getMovement().getName());
    }

    /**
     * Public method for parsing Properties into Artwork's fields
     * @author Zerina Jasarspahic
     */
    public ArtWork toArtwork(){
        ArtWork aw = new ArtWork();
        aw.setName(this.name.getValue());
        aw.setArtist(this.artist.getValue());
        aw.setMovement(this.artStyle.getValue());
        return aw;
    }
}
