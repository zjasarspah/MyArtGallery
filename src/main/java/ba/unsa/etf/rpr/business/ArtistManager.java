package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import java.util.Arrays;
import java.util.List;

/**
 * Business Logic Layer for Artist
 * @author Zerina Jasarspahic
 */
public class ArtistManager implements Manager<Artist> {

    public List<Artist> getAll() throws ArtGalleryException {
        return DaoFactory.artistDao().getAll();
    }

    public void delete(Integer id) throws ArtGalleryException {
        try{
            DaoFactory.artistDao().delete(id);
        } catch (ArtGalleryException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new ArtGalleryException("Can't delete artist which is related to artwork. First delete related artwork before deleting artist.");
            }
            throw e;
        }
    }

    public Artist getById(Integer id) throws ArtGalleryException {
        return DaoFactory.artistDao().getById(id);
    }

    public void update(Artist artist) throws ArtGalleryException{
        validateArtist(artist);
        DaoFactory.artistDao().update(artist);
    }

    public Artist add(Artist artist) throws ArtGalleryException{
        validateArtist(artist);
        (new ArtistManager()).isExist(artist);
        return DaoFactory.artistDao().add(artist);
    }

    public List<Artist> searchByName(String name) throws ArtGalleryException {
        return DaoFactory.artistDao().searchByName(name);
    }

    private void isExist(Artist artist) throws ArtGalleryException {
        for (Artist a : getAll()) {
            if (a.equals(artist)) {
                throw new ArtGalleryException("This artist already exists.");
            }
        }
    }

    public void validateArtist(Artist a) throws ArtGalleryException{
        if (a.getName() == null || a.getName().length() > 45 || a.getName().length() < 3){
            throw new ArtGalleryException("Artist's name must be between 3 and 45 chars long.");
        } else if (!isLifeSpanCorrect(a.getLifespan())) {
            throw new ArtGalleryException("Lifespan of the artist isn't correct.");
        } else if (a.getNationality().length() > 20) {
            throw new ArtGalleryException("Artist's nationality must be less than 20 chars long.");
        }
    }

    private boolean isLifeSpanCorrect (String lifeSpan) {
        if (lifeSpan == null || lifeSpan.length() != 9 || lifeSpan.charAt(4) != '-') {
            return false;
        }

        for (int i = 0; i < lifeSpan.length(); i++) {
            if (i == 4) continue;
            if (lifeSpan.charAt(i) < '0' || lifeSpan.charAt(i) > '9') {
                return false;
            }
        }

        List<String> years = Arrays.asList(lifeSpan.split("-"));
        return years.get(0).compareTo(years.get(1)) < 0 && Integer.parseInt(years.get(1)) - Integer.parseInt(years.get(0)) <= 100 && Integer.parseInt(years.get(1)) - Integer.parseInt(years.get(0)) >= 15;
    }
}