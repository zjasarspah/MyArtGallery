package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * List of possible artworks in art gallery
 * @author Zerina Jasarspahic
 */

public class ArtWork implements Idable {
    private Integer id;
    private ArtStyle artStyle;
    private Artist artist;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArtStyle getArtStyle() {
        return artStyle;
    }

    public void setArtStyle(ArtStyle artStyle) {
        this.artStyle = artStyle;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtWork artWork = (ArtWork) o;
        return Objects.equals(artStyle, artWork.artStyle) && Objects.equals(artist, artWork.artist) && Objects.equals(name, artWork.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artStyle, artist, name);
    }
}
