package ba.unsa.etf.rpr.domain;

/**
 * Interface that forces all POJO beans to have ID field
 * @author Zerina Jasarspahic
 */

public interface Idable {
    void setId(Integer id);
    Integer getId();
}
