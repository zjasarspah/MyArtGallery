package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.ArtWork;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * Business Logic Layer for Artwork
 * @author Zerina Jasarspahic
 */
public class ArtWorkManager implements Manager<ArtWork> {

    public List<ArtWork> getAll() throws ArtGalleryException {
        return DaoFactory.artWorkDao().getAll();
    }

    public void delete(Integer id) throws ArtGalleryException {
        DaoFactory.artWorkDao().delete(id);
    }

    public ArtWork getById(Integer id) throws ArtGalleryException {
        return DaoFactory.artWorkDao().getById(id);
    }

    public void update(ArtWork artWork) throws ArtGalleryException{
        validateArtWork(artWork);
        DaoFactory.artWorkDao().update(artWork);
    }

    public ArtWork add(ArtWork artWork) throws ArtGalleryException{
        validateArtWork(artWork);
        (new ArtWorkManager()).isExist(artWork);
        return DaoFactory.artWorkDao().add(artWork);
    }

    public List<ArtWork> searchByName (String name) throws ArtGalleryException {
        return DaoFactory.artWorkDao().searchByName(name);
    }

    /**
     * Method that returns the list of artworks from the database that have the same art style
     * @param artStyle we search
     * @throws ArtGalleryException if art style doesn't exist in the database
     */
    private List<ArtWork> searchByArtStyle(ArtStyle artStyle) throws ArtGalleryException {
        return DaoFactory.artWorkDao().searchByArtStyle(artStyle);
    }

    /**
     * Method that returns the list of artworks from the database that have the same artist
     * @param artist we search
     * @throws ArtGalleryException if artist doesn't exist in the database
     */
    private List<ArtWork> searchByArtist(Artist artist) throws ArtGalleryException {
        return DaoFactory.artWorkDao().searchByArtist(artist);
    }

    /**
     * Method that returns the list of artworks from the database that have the same name, artist or art style as input item
     * @param item we search
     * @throws ArtGalleryException if item wasn't found in the database
     */
    public List<ArtWork> search(String item) throws ArtGalleryException {
        /* TreeSet is used because we don't want duplication in searching */
        /* Elements in TreeSet are sorted */
        /* Because we have a TreeSet of art works (type ArtWork), it is necessary to define a criterion by which the elements in the TreeSet are compared (in this case it is field id)*/
        TreeSet<ArtWork> set = new TreeSet<>(Comparator.comparing(ArtWork::getId));
        set.addAll((new ArtWorkManager()).searchByName(item));
        List<Artist> a = (new ArtistManager()).searchByName(item);
        for (Artist artist : a) {
            set.addAll((new ArtWorkManager()).searchByArtist(artist));
        }
        List<ArtStyle> as = (new ArtStyleManager()).searchByName(item);
        for (ArtStyle style : as) {
            set.addAll((new ArtWorkManager()).searchByArtStyle(style));
        }

        return new ArrayList<>(set);
    }

    /**
     * Method for checking are the information about the artwork correct
     * @param artwork for checking
     * @throws ArtGalleryException if information about the artwork are not correct
     */
    public void validateArtWork(ArtWork artwork) throws ArtGalleryException{
        if (artwork.getName() == null || artwork.getName().length() > 45 || artwork.getName().length() < 3){
            throw new ArtGalleryException("Artwork's name must be between 3 and 45 chars long.");
        } else if (artwork.getArtist() == null) {
            throw new ArtGalleryException("You forget to fill out information about Artist.");
        } else if (artwork.getArtStyle() == null) {
            throw new ArtGalleryException("You forget to fill out information about Art Style.");
        }
    }

    /**
     * Method for checking does the artwork already exist in the database
     * @param artwork we search
     * @throws ArtGalleryException if the artwork already exists in the database
     */
    private void isExist(ArtWork artwork) throws ArtGalleryException {
        for (ArtWork aw : getAll()) {
            if (aw.equals(artwork)) {
                throw new ArtGalleryException("This artwork already exists.");
            }
        }
    }
}