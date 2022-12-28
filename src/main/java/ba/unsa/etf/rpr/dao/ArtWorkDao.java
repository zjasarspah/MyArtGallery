package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.ArtWork;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;

import java.util.List;

/**
 * Dao interface for ArtWork domain bean
 * @author Zerina Jasarspahic
 */

public interface ArtWorkDao extends Dao<ArtWork> {

    /**
     * Returns all artworks that belong to given art style
     * @param movement search movement
     * @return list of artworks
     */

    List<ArtWork> searchByArtStyle(ArtStyle movement) throws ArtGalleryException;

    /**
     * Returns all artworks that belong to given artist
     * @param artist search artist
     * @return list of artworks
     */

    List<ArtWork> searchByArtist(Artist artist) throws ArtGalleryException;
}
