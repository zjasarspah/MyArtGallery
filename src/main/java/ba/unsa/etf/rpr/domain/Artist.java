package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * List of possible artists in art gallery
 * @author Zerina Jasarspahic
 */

public class Artist implements Idable{

    private int id;
    private String name;
    private String lifespan;

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

    public String getLifespan() {
        return lifespan;
    }

    public void setLifespan(String lifespan) {
        this.lifespan = lifespan;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return id == artist.id && Objects.equals(name, artist.name) && Objects.equals(lifespan, artist.lifespan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name,  lifespan);
    }
}