package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.Artist;
import java.util.List;

/**
 * Dao interface for Artist domain bean
 * @author Zerina Jasarspahic
 */

public interface ArtistDao extends Dao<Artist> {

    /**
     * Returns all artists that belong to given art style
     * @param movement search movement
     * @return list of artists
     */

    List<Artist> searchByArtStyle(ArtStyle movement);

    /**
     * Returns all artists that belong to given nationality
     * @param nationality
     * @return list of artists
     */

    List<Artist> searchByNationality(String nationality);

}