package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.ArtWork;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import java.util.List;

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
        DaoFactory.artWorkDao().update(artWork);
    }

    public ArtWork add(ArtWork artWork) throws ArtGalleryException{
        return DaoFactory.artWorkDao().add(artWork);
    }

}