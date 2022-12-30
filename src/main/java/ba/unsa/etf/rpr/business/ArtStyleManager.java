package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import java.util.List;

/**
 * Business Logic Layer for Art style
 * @author Zerina Jasarspahic
 */
public class ArtStyleManager implements Manager<ArtStyle> {

    public List<ArtStyle> getAll() throws ArtGalleryException {
        return DaoFactory.artStyleDao().getAll();
    }

    public void delete(int id) throws ArtGalleryException {
        DaoFactory.artStyleDao().delete(id);
    }

    public ArtStyle getById(int id) throws ArtGalleryException {
        return DaoFactory.artStyleDao().getById(id);
    }

    public void update(ArtStyle artStyle) throws ArtGalleryException{
        DaoFactory.artStyleDao().update(artStyle);
    }

    public ArtStyle add(ArtStyle artStyle) throws ArtGalleryException{
        return DaoFactory.artStyleDao().add(artStyle);
    }

}