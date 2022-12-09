package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * List of possible artists in art gallery
 * @author Zerina Jasarspahic
 */

public class Artist {

    private int id;
    private String name;
    private String nationality;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return id == artist.id && Objects.equals(name, artist.name) && Objects.equals(nationality, artist.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name,  nationality);
    }
}