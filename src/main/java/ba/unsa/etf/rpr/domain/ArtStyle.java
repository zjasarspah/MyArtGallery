package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * List of possible art styles in art gallery
 * @author Zerina Jasarspahic
 */

public class ArtStyle {

    private int id;
    private String name;
    private String year;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year);
    }

    @Override
    public String toString() {
        return "ArtStyle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtStyle artStyle = (ArtStyle) o;
        return id == artStyle.id && Objects.equals(name, artStyle.name) && Objects.equals(year, artStyle.year);
    }
}
