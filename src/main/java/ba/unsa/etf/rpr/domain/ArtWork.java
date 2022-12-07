package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * List of possible artworks in art gallery
 * @author Zerina Jasarspahic
 */

public class ArtWork {

    private int id;
    private ArtStyle movement;
    private Artist artist;
    private String name;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArtStyle getMovement() {
        return movement;
    }

    public void setMovement(ArtStyle movement) {
        this.movement = movement;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ArtWork{" +
                "id=" + id +
                ", movement=" + movement +
                ", artist=" + artist +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtWork artWork = (ArtWork) o;
        return id == artWork.id && Double.compare(artWork.price, price) == 0 && Objects.equals(movement, artWork.movement) && Objects.equals(artist, artWork.artist) && Objects.equals(name, artWork.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movement, artist, name, price);
    }
}
