package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.ArtWork;
import ba.unsa.etf.rpr.domain.Artist;

import java.util.List;

/**
 * Dao interface for ArtWork domain bean
 * @author Zerina Jasarspahic
 */

public interface ArtWorkDao extends Dao<ArtWork> {

    /**
     * Returns all artworks that have price lower than given price
     * @param price search price
     * @return list of artworks
     */
    public List<ArtWork> searchByPrice (int price);

    /**
     * Returns all artworks that belong to given art style
     * @param movement search movement
     * @return list of artworks
     */

    List<ArtWork> searchByArtStyle(ArtStyle movement);

    /**
     * Returns all artworks that belong to given artist
     * @param artist search artist
     * @return list of artworks
     */

    List<ArtWork> searchByArtist(Artist artist);
}
