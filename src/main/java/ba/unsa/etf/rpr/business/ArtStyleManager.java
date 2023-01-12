package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;

import java.util.Arrays;
import java.util.List;

/**
 * Business Logic Layer for Art Style
 * @author Zerina Jasarspahic
 */
public class ArtStyleManager implements Manager<ArtStyle> {

    public List<ArtStyle> getAll() throws ArtGalleryException {
        return DaoFactory.artStyleDao().getAll();
    }

    public void delete(Integer id) throws ArtGalleryException {
        try{
            DaoFactory.artStyleDao().delete(id);
        }catch (ArtGalleryException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new ArtGalleryException("Can't delete art style which is related to artwork. First delete related artwork before deleting artstyle.");
            }
            throw e;
        }
    }

    public ArtStyle getById(Integer id) throws ArtGalleryException {
        return DaoFactory.artStyleDao().getById(id);
    }

    public void update(ArtStyle artStyle) throws ArtGalleryException{
        validateArtStyle(artStyle);
        DaoFactory.artStyleDao().update(artStyle);
    }

    public ArtStyle add(ArtStyle artStyle) throws ArtGalleryException{
        validateArtStyle(artStyle);
        (new ArtStyleManager()).isExist(artStyle);
        return DaoFactory.artStyleDao().add(artStyle);

    }

    public List<ArtStyle> searchByName (String name) throws ArtGalleryException {
        return DaoFactory.artStyleDao().searchByName(name);
    }

    private void isExist(ArtStyle artStyle) throws ArtGalleryException {
        for (ArtStyle as : getAll()) {
            if (as.equals(artStyle)) {
                throw new ArtGalleryException("This art style already exists.");
            }
        }
    }
    public void validateArtStyle(ArtStyle as) throws ArtGalleryException{
        if (as.getName() == null || as.getName().length() > 45 || as.getName().length() < 3){
            throw new ArtGalleryException("Art Style's name must be between 3 and 45 chars long.");
        } else if (!isDurationCorrect(as.getDuration())) {
            throw new ArtGalleryException("Duration of the art style isn't correct.");
        }
    }

    private boolean isDurationCorrect (String duration) {
        if (duration == null || duration.length() != 9 || duration.charAt(4) != '-') {
            return false;
        }

        for (int i = 0; i < duration.length(); i++) {
            if (i == 4) continue;
            if (duration.charAt(i) < '0' || duration.charAt(i) > '9') {
                return false;
            }
        }

        List<String> years = Arrays.asList(duration.split("-"));
        return years.get(0).compareTo(years.get(1)) < 0;
    }

}