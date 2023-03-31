package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * List of possible artists in art gallery
 * @author Zerina Jasarspahic
 */

public class Artist implements Idable{
    private Integer id;
    private String name;
    private String lifespan;
    private String nationality;

    public Artist(String name, String lifespan) {
        this.name = name;
        this.lifespan = lifespan;
        this.nationality = "No information";
    }

    public Artist(String name, String lifespan, String nationality) {
        this.name = name;
        this.lifespan = lifespan;
        this.nationality = nationality;
    }

    public Artist() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifespan() {
        return lifespan;
    }

    public void setLifespan(String lifespan) {
        this.lifespan = lifespan;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return name + " (" + lifespan + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name,  lifespan, nationality);
    }
}