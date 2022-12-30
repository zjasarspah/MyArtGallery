package ba.unsa.etf.rpr.controllers;
import ba.unsa.etf.rpr.business.ArtStyleManager;
import ba.unsa.etf.rpr.business.ArtWorkManager;
import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.business.Manager;
import ba.unsa.etf.rpr.controllers.components.MainComponents;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;


public class MainController {

    public TabPane tabScreen;
    private final ArtistManager artistManager = new ArtistManager();
    private final ArtStyleManager artStyleManager = new ArtStyleManager();

    private final ArtWorkManager artWorkManager = new ArtWorkManager();
    @FXML
    public TableView<Object> artistTableView;
    public TableColumn<Object, Object> colArtistId;
    public TableColumn<Object, Object> colArtistName;
    public TableColumn<Object, Object> actionColumnArtist;
    public TableColumn<Object, Object> colArtStyleId;
    public TableColumn<Object, Object> colArtStyleName;
    public TableColumn<Object, Object> actionColumnArtStyle;
    public TableView<Object> artStyleTableView;

    public TableColumn<Object, Object> colArtworkId;
    public TableColumn<Object, Object> colArtworkName;
    public TableColumn<Object, Object> actionColumnArtwork;
    public TableView<Object> artworkTableView;

    private ObservableList<Object> artStyles;
    private ObservableList<Object> artists;
    private ObservableList<Object> artworks;

    @FXML
    public void initialize() throws ArtGalleryException {
        (new MainComponents()).initialize(tabScreen,artistTableView, colArtistId,colArtistName, actionColumnArtist, artists, artistManager,"/fxml/viewArtist.fxml", "/fxml/editArtist.fxml");
        (new MainComponents()).initialize(tabScreen,artStyleTableView, colArtStyleId,colArtStyleName, actionColumnArtStyle, artStyles, artStyleManager,"/fxml/viewArtStyle.fxml","/fxml/editArtStyle.fxml");
        (new MainComponents()).initialize(tabScreen,artworkTableView, colArtworkId,colArtworkName, actionColumnArtwork, artworks, artWorkManager, "/fxml/viewArtWork.fxml", "/fxml/editArtWork.fxml");
    }
}