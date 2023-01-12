package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.ArtStyleManager;
import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test class for Art Style
 * @author Zerina Jasarspahic
 *
 */
class ArtStyleManagerTest {

    private ArtStyleManager artStyleManager;
    private ArtStyle artStyle;

    /**
     * This method will be called before each test method
     */

    @BeforeEach
    public void initializeObjectsWeNeed() {
        artStyleManager = Mockito.mock(ArtStyleManager.class);
        artStyle = new ArtStyle();
        artStyle.setName("Constructivism");
        artStyle.setDuration("1915-1940");
        artStyle.setId(20);
    }

    /**
     * In this method it will be tested validateArtStyleName for correct and incorrect name of the art style
     */

    @Test
    void validateArtStyleName() throws ArtGalleryException {
        ArtStyle correctName = new ArtStyle("Futurism", "1908-1944");
        try {
            Mockito.doCallRealMethod().when(artStyleManager).validateArtStyle(correctName);
        } catch (ArtGalleryException e) {
            e.printStackTrace();
            Assertions.fail();
        }

        ArtStyle incorrectNameShort = new ArtStyle("F", "1908-1944");
        Mockito.doCallRealMethod().when(artStyleManager).validateArtStyle(incorrectNameShort);
        ArtGalleryException asException1 = Assertions.assertThrows(ArtGalleryException.class, () -> artStyleManager.validateArtStyle(incorrectNameShort), "Art Style's name must be between 3 and 45 chars long.");
        Assertions.assertEquals("Art Style's name must be between 3 and 45 chars long.", asException1.getMessage());

        ArtStyle incorrectNameLong = new ArtStyle(RandomStringUtils.randomAlphabetic(50), "1908-1944");
        Mockito.doCallRealMethod().when(artStyleManager).validateArtStyle(incorrectNameLong);
        ArtGalleryException asException2 = Assertions.assertThrows(ArtGalleryException.class, () -> artStyleManager.validateArtStyle(incorrectNameLong), "Art Style's name must be between 3 and 45 chars long.");
        Assertions.assertEquals("Art Style's name must be between 3 and 45 chars long.", asException2.getMessage());
    }

    /**
     * In this method it will be tested validateArtStyleDuration for correct and incorrect duration of the art style
     */

    @Test
    void validateArtStyleDuration() throws ArtGalleryException{

        ArtStyle incorrectDuration = new ArtStyle("Futurism", "1908-1900");
        Mockito.doCallRealMethod().when(artStyleManager).validateArtStyle(incorrectDuration);
        ArtGalleryException asException3 = Assertions.assertThrows(ArtGalleryException.class, () -> artStyleManager.validateArtStyle(incorrectDuration), "Duration of the art style isn't correct.");
        Assertions.assertEquals("Duration of the art style isn't correct.", asException3.getMessage());

        ArtStyle incorrectDurationSize = new ArtStyle("Futurism", "198-1944");
        Mockito.doCallRealMethod().when(artStyleManager).validateArtStyle(incorrectDurationSize);
        ArtGalleryException asException4 = Assertions.assertThrows(ArtGalleryException.class, () -> artStyleManager.validateArtStyle(incorrectDurationSize), "Duration of the art style isn't correct.");
        Assertions.assertEquals("Duration of the art style isn't correct.", asException4.getMessage());
    }

    /**
     * Adding a new art style
     */

    @Test
    void addNewArtStyle() throws ArtGalleryException {
        artStyleManager.add(artStyle);
        Assertions.assertTrue(true);
        Mockito.verify(artStyleManager).add(artStyle);
    }

    /**
     * Adding a new art style that already exists
     */
    @Test
    void addNewArtStyleException() throws ArtGalleryException {
        ArtStyle newArtStyle = new ArtStyle("Cubism", "1907-1914");
        Mockito.doCallRealMethod().when(artStyleManager).add(newArtStyle);
        ArtGalleryException asException = Assertions.assertThrows(ArtGalleryException.class, () -> artStyleManager.add(newArtStyle), "This art style already exists.");
        Assertions.assertEquals("This art style already exists.", asException.getMessage());
    }

    /**
     * Deleting an art style that is related to artwork
     */

    @Test
    void deleteArtStyleException() throws ArtGalleryException {
        ArtStyle newArtStyle = new ArtStyle("Cubism", "1907-1914");
        newArtStyle.setId(5);

        Mockito.doCallRealMethod().when(artStyleManager).delete(newArtStyle.getId());
        ArtGalleryException asException = Assertions.assertThrows(ArtGalleryException.class, () -> artStyleManager.delete(newArtStyle.getId()), "Can't delete art style which is related to artwork. First delete related artwork before deleting artstyle.");
        Assertions.assertEquals("Can't delete art style which is related to artwork. First delete related artwork before deleting artstyle.", asException.getMessage());
    }
}