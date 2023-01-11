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

    public void delete(int id) throws ArtGalleryException {
        DaoFactory.artWorkDao().delete(id);
    }

    public ArtWork getById(int id) throws ArtGalleryException {
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

    private List<ArtWork> searchByArtStyle(ArtStyle movement) throws ArtGalleryException {
        return DaoFactory.artWorkDao().searchByArtStyle(movement);
    }

    private List<ArtWork> searchByArtist(Artist artist) throws ArtGalleryException {
        return DaoFactory.artWorkDao().searchByArtist(artist);
    }

    public List<ArtWork> search(String item) throws ArtGalleryException {
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

    private void validateArtWork(ArtWork aw) throws ArtGalleryException{
        if (aw.getName() == null || aw.getName().length() > 45 || aw.getName().length() < 3){
            throw new ArtGalleryException("Artwork's name must be between 3 and 45 chars long.");
        } else if (aw.getArtist() == null) {
            throw new ArtGalleryException("You forget to fill out information about Artist.");
        } else if (aw.getArtStyle() == null) {
            throw new ArtGalleryException("You forget to fill out information about Art Style.");
        }
    }

    private void isExist(ArtWork artwork) throws ArtGalleryException {
        for (ArtWork aw : getAll()) {
            if (aw.equals(artwork)) {
                throw new ArtGalleryException("This artwork already exists.");
            }
        }
    }
}