package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * List of possible art styles in art gallery
 * @author Zerina Jasarspahic
 */

public class ArtStyle implements Idable{

    private Integer id;
    private String name;
    private String duration;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration);
    }

    @Override
    public String toString() {
        return name + " (" + duration + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtStyle artStyle = (ArtStyle) o;
        return Objects.equals(name, artStyle.name);
    }
}
