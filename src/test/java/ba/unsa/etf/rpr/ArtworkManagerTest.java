package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.ArtWorkManager;
import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.ArtWork;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test class for Artwork
 * @author Zerina Jasarspahic
 *
 */

class ArtworkManagerTest {

    private ArtWorkManager artworkManager;
    private ArtWork artwork;

    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed() {
        artworkManager = Mockito.mock(ArtWorkManager.class);
        artwork = new ArtWork();
        artwork.setName("The Persistence of Memory");
        artwork.setArtist(new Artist("Salvador Dali","1904-1989"));
        artwork.setArtStyle(new ArtStyle("Surrealism","1910-1920"));
    }

    /**
     * In this method it will be tested validateArtworkName for correct and incorrect name of the artwork
     */
    @Test
    void validateArtworkName() throws ArtGalleryException {
        try {
            Mockito.doCallRealMethod().when(artworkManager).validateArtWork(artwork);
        } catch (ArtGalleryException e) {
            e.printStackTrace();
            Assertions.fail();
        }

        ArtWork incorrectNameShort = new ArtWork("F", new Artist("Salvador Dali","1904-1989"), new ArtStyle("Surrealism","1910-1920"));
        Mockito.doCallRealMethod().when(artworkManager).validateArtWork(incorrectNameShort);
        ArtGalleryException asException1 = Assertions.assertThrows(ArtGalleryException.class, () -> artworkManager.validateArtWork(incorrectNameShort), "Artwork's name must be between 3 and 45 chars long.");
        Assertions.assertEquals("Artwork's name must be between 3 and 45 chars long.", asException1.getMessage());

        ArtWork incorrectNameLong = new ArtWork(RandomStringUtils.randomAlphabetic(50), new Artist("Salvador Dali","1904-1989"), new ArtStyle("Surrealism","1910-1920"));
        Mockito.doCallRealMethod().when(artworkManager).validateArtWork(incorrectNameLong);
        ArtGalleryException asException2 = Assertions.assertThrows(ArtGalleryException.class, () -> artworkManager.validateArtWork(incorrectNameLong), "Artwork's name must be between 3 and 45 chars long.");
        Assertions.assertEquals("Artwork's name must be between 3 and 45 chars long.", asException2.getMessage());
    }

    /**
     * In this method it will be tested validateArtist if artist field is equal to null
     */
    @Test
    void validateArtist() throws ArtGalleryException{

        ArtWork incorrectArtist = new ArtWork("The Persistence of Memory", null , new ArtStyle("Surrealism","1910-1920"));
        Mockito.doCallRealMethod().when(artworkManager).validateArtWork(incorrectArtist);
        ArtGalleryException asException3 = Assertions.assertThrows(ArtGalleryException.class, () -> artworkManager.validateArtWork(incorrectArtist), "You forget to fill out information about Artist.");
        Assertions.assertEquals("You forget to fill out information about Artist.", asException3.getMessage());
    }

    /**
     * In this method it will be tested validateArtStyle if artist field is equal to null
     */
    @Test
    void validateArtStyle() throws ArtGalleryException{

        ArtWork incorrectArtStyle = new ArtWork("The Persistence of Memory", new Artist("Salvador Dali","1904-1989"), null );
        Mockito.doCallRealMethod().when(artworkManager).validateArtWork(incorrectArtStyle);
        ArtGalleryException asException3 = Assertions.assertThrows(ArtGalleryException.class, () -> artworkManager.validateArtWork(incorrectArtStyle), "You forget to fill out information about Art Style.");
        Assertions.assertEquals("You forget to fill out information about Art Style.", asException3.getMessage());
    }

    /**
     * Adding a new artwork
     */
    @Test
    void addNewArtwork() throws ArtGalleryException {
        artworkManager.add(artwork);
        Assertions.assertTrue(true);
        Mockito.verify(artworkManager).add(artwork);
    }

    /**
     * Adding a new artwork that already exists
     */
    @Test
    void addNewArtworkException() throws ArtGalleryException {
        ArtWork newArtwork = new ArtWork("Mona Lisa", new Artist("Leonardo da Vinci", "1452-1519"), new ArtStyle("Renaissance", "1300-1520"));
        Mockito.doCallRealMethod().when(artworkManager).add(newArtwork);
        ArtGalleryException asException = Assertions.assertThrows(ArtGalleryException.class, () -> artworkManager.add(newArtwork), "This artwork already exists.");
        Assertions.assertEquals("This artwork already exists.", asException.getMessage());
    }
}