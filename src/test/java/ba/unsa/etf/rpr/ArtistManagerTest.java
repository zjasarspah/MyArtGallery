package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test class for Artist
 * @author Zerina Jasarspahic
 *
 */
class ArtistManagerTest {

    private ArtistManager artistManager;
    private Artist artist;

    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed() {
        artistManager = Mockito.mock(ArtistManager.class);
        artist = new Artist();
        artist.setName("Leonardo da Vinci");
        artist.setLifespan("1452–1519");
        artist.setId(20);
    }

    /**
     * In this method it will be tested validateArtistName for correct and incorrect name of the artist
     */

    @Test
    void validateArtistName() throws ArtGalleryException {
        Artist correct = new Artist("Raffaelo Santi", "1483-1520");
        try {
            Mockito.doCallRealMethod().when(artistManager).validateArtist(correct);
        } catch (ArtGalleryException e) {
            e.printStackTrace();
            Assertions.fail();
        }

        Artist incorrectNameShort = new Artist("F", "1908-1944");
        Mockito.doCallRealMethod().when(artistManager).validateArtist(incorrectNameShort);
        ArtGalleryException asException1 = Assertions.assertThrows(ArtGalleryException.class, () -> artistManager.validateArtist(incorrectNameShort), "Artist's name must be between 3 and 45 chars long.");
        Assertions.assertEquals("Artist's name must be between 3 and 45 chars long.", asException1.getMessage());

        Artist incorrectNameLong = new Artist(RandomStringUtils.randomAlphabetic(50), "1908-1944");
        Mockito.doCallRealMethod().when(artistManager).validateArtist(incorrectNameLong);
        ArtGalleryException asException2 = Assertions.assertThrows(ArtGalleryException.class, () -> artistManager.validateArtist(incorrectNameLong), "Artist's name must be between 3 and 45 chars long.");
        Assertions.assertEquals("Artist's name must be between 3 and 45 chars long.", asException2.getMessage());
    }

    /**
     * In this method it will be tested validateArtistLifespan for correct and incorrect lifespan of the artist
     */

    @Test
    void validateArtistLifespan() throws ArtGalleryException{

        Artist incorrectLifespan = new Artist("Pablo Picasso", "1908-1900");
        Mockito.doCallRealMethod().when(artistManager).validateArtist(incorrectLifespan);
        ArtGalleryException asException3 = Assertions.assertThrows(ArtGalleryException.class, () -> artistManager.validateArtist(incorrectLifespan), "Lifespan of the artist isn't correct.");
        Assertions.assertEquals("Lifespan of the artist isn't correct.", asException3.getMessage());

        Artist incorrectDurationSize = new Artist("Giorgione", "198-1944");
        Mockito.doCallRealMethod().when(artistManager).validateArtist(incorrectDurationSize);
        ArtGalleryException asException4 = Assertions.assertThrows(ArtGalleryException.class, () -> artistManager.validateArtist(incorrectDurationSize), "Lifespan of the artist isn't correct.");
        Assertions.assertEquals("Lifespan of the artist isn't correct.", asException4.getMessage());

    }
    /**
     * Adding a new artist
     */

    @Test
    void addNewArtist() throws ArtGalleryException {
        Artist newArtist = new Artist("Paolo Uccello", "1397-1475");
        artistManager.add(newArtist);
        Assertions.assertTrue(true);
        Mockito.verify(artistManager).add(newArtist);
    }

    /**
     * Adding a new artist that already exists
     */

    @Test
    void addNewArtistException() throws ArtGalleryException {

        Mockito.doCallRealMethod().when(artistManager).add(artist);
        ArtGalleryException asException = Assertions.assertThrows(ArtGalleryException.class, () -> artistManager.add(artist), "This artist already exists.");
        Assertions.assertEquals("This artist already exists.", asException.getMessage());
    }

    /**
     * Deleting an artist that is related to artwork
     */

    @Test
    void deleteArtistException() throws ArtGalleryException {
        artist.setId(1);

        Mockito.doCallRealMethod().when(artistManager).delete(artist.getId());
        ArtGalleryException asException = Assertions.assertThrows(ArtGalleryException.class, () -> artistManager.delete(artist.getId()), "Can't delete artist which is related to artwork. First delete related artwork before deleting artist.");
        Assertions.assertEquals("Can't delete artist which is related to artwork. First delete related artwork before deleting artist.", asException.getMessage());
    }
}