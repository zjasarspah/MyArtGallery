package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import java.util.List;

/**
 * Business Logic Layer for Artist
 * @author Zerina Jasarspahic
 */

//ova klasa se napravila radi citljivosti jer ako u kodu za kontroler napisemo (new DaoFactory).artistDao().getAll(); komplikuje se kod
    //također ovo je napravljeno
public class ArtistManager implements Manager<Artist> {

    public List<Artist> getAll() throws ArtGalleryException {
        return DaoFactory.artistDao().getAll();
    }

    public void delete(int id) throws ArtGalleryException {
        DaoFactory.artistDao().delete(id);
    }

    public Artist getById(int id) throws ArtGalleryException {
        return DaoFactory.artistDao().getById(id);
    }

    public void update(Artist artist) throws ArtGalleryException{
        DaoFactory.artistDao().update(artist);
    }

    public Artist add(Artist artist) throws ArtGalleryException{
        return DaoFactory.artistDao().add(artist);
    }

}