package ba.unsa.etf.rpr.controllers;
import ba.unsa.etf.rpr.business.ArtStyleManager;
import ba.unsa.etf.rpr.business.ArtWorkManager;
import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.business.Manager;
import ba.unsa.etf.rpr.controllers.artist.ArtistController;
import ba.unsa.etf.rpr.controllers.artist.ViewArtistController;
import ba.unsa.etf.rpr.controllers.artstyle.ArtStyleController;
import ba.unsa.etf.rpr.controllers.artstyle.ViewArtStyleController;
import ba.unsa.etf.rpr.controllers.artwork.ArtworkController;
import ba.unsa.etf.rpr.controllers.artwork.ViewArtworkController;
import ba.unsa.etf.rpr.controllers.components.TripleButtonCellFactory;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.util.*;

/**
 * Controller for main window
 * @author Zerina Jasarspahic
 */
public class MainEmployeeController {

    @FXML
    public TabPane tabScreen;
    @FXML
    public TableView<Object> artistTableView;
    @FXML
    public TableColumn<Object, Object> colArtistId;
    @FXML
    public TableColumn<Object, Object> actionColumnArtist;
    @FXML
    public TableColumn<Object, Object> colArtistName;
    @FXML
    public TableColumn<Object, Object> colArtStyleId;
    @FXML
    public TableColumn<Object, Object> colArtStyleName;
    @FXML
    public TableColumn<Object, Object> actionColumnArtStyle;
    @FXML
    public TableView<Object> artStyleTableView;
    @FXML
    public TableColumn<Object, Object> colArtworkId;
    @FXML
    public TableColumn<Object, Object> colArtworkName;
    @FXML
    public TableColumn<Object, Object> actionColumnArtwork;
    @FXML
    public TableView<Object> artworkTableView;
    @FXML
    public Button btnAddArtStyle;
    @FXML
    public Button btnAddArtWork;
    @FXML
    public Button btnAddArtist;
    @FXML
    public Button btnRefreshArtists;
    @FXML
    public Button btnRefreshArtworks;
    @FXML
    public Button btnRefreshArtStyles;
    @FXML
    public TextField txtFieldSearchArtworks;
    @FXML
    public TextField txtFieldSearchArtist;
    @FXML
    public TextField txtFieldSearchArtStyle;
    private final ArtistManager artistManager = new ArtistManager();
    private final ArtStyleManager artStyleManager = new ArtStyleManager();
    private final ArtWorkManager artWorkManager = new ArtWorkManager();
    private ObservableList<Object> artStyles;
    private ObservableList<Object> artists;
    private ObservableList<Object> artworks;

    /**
     * Public method for button that adds the artist
     */
    public void btnActionAddArtist(ActionEvent event){
        editAddScene(new ArtistController(null), tabScreen, null,"/artist/addArtist.fxml", "Add Artist");
    }

    /**
     * Public method for button that adds the art style
     */
    public void btnActionAddArtStyle(ActionEvent actionEvent) {
        editAddScene(new ArtStyleController(null), tabScreen, null,"/artstyle/addArtStyle.fxml", "Add Art Style");
    }

    /**
     * Public method for button that adds the artwork
     */
    public void btnActionAddArtWork(ActionEvent actionEvent) {
        editAddScene(new ArtworkController (null), tabScreen, null, "/artwork/addArtwork.fxml", "Add Artwork");
    }

    /**
     * Public method for button that refreshes list of artworks
     */
    public void btnActionRefreshArtworks(ActionEvent actionEvent) throws ArtGalleryException {
        artworks = FXCollections.observableArrayList(artWorkManager.getAll());
        setColumns(colArtworkId, colArtworkName, actionColumnArtwork);
        setButtonsInArtworkColumns (actionColumnArtwork, tabScreen, artWorkManager);
        refresh(artworkTableView, artworks);
    }

    /**
     * Public method for button that refreshes list of artists
     */
    public void btnActionRefreshArtists(ActionEvent actionEvent) throws ArtGalleryException {
        artists = FXCollections.observableArrayList(artistManager.getAll());
        setColumns(colArtistId, colArtistName, actionColumnArtist);
        setButtonsInArtistColumns (actionColumnArtist, tabScreen, artistManager);
        refresh(artistTableView, artists);
    }

    /**
     * Public method for button that refresh list of art styles
     */
    public void btnActionRefreshArtStyles(ActionEvent actionEvent) throws ArtGalleryException {
        artStyles = FXCollections.observableArrayList(artStyleManager.getAll());
        setColumns(colArtStyleId, colArtStyleName, actionColumnArtStyle);
        setButtonsInArtStyleColumns (actionColumnArtStyle, tabScreen, artStyleManager);
        refresh(artStyleTableView, artStyles);
    }

    /**
     * Private method for setting columns
     */
    private static void setColumns (TableColumn<Object, Object> columnId, TableColumn<Object, Object> columnName, TableColumn<Object, Object> actionColumn) {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    /**
     * Private method for setting buttons in columns for each art style
     * View, Delete and Edit buttons
     */
    private static void setButtonsInArtStyleColumns (TableColumn<Object, Object> actionColumn, TabPane tabScreen, Manager manager) {
        actionColumn.setCellFactory(new TripleButtonCellFactory(viewEvent -> {
            int id = Integer.parseInt(((Button) viewEvent.getSource()).getUserData().toString());
            editAddScene(new ViewArtStyleController(id), tabScreen, id, "/artstyle/viewArtStyle.fxml",  "View ArtStyle");
        }, editEvent -> {
            int id = Integer.parseInt(((Button) editEvent.getSource()).getUserData().toString());
            editAddScene(new ArtStyleController(id),tabScreen, id, "/artstyle/addArtStyle.fxml", "Edit Art Style");
        }, deleteEvent -> {
            int id = Integer.parseInt(((Button) deleteEvent.getSource()).getUserData().toString());
            delete(id, manager);
        }));
    }

    /**
     * Private method for setting buttons in columns for each artist
     * View, Delete and Edit buttons
     */
    private static void setButtonsInArtistColumns (TableColumn<Object, Object> actionColumn, TabPane tabScreen, Manager manager) {
        actionColumn.setCellFactory(new TripleButtonCellFactory(viewEvent -> {
            int id = Integer.parseInt(((Button) viewEvent.getSource()).getUserData().toString());
            editAddScene(new ViewArtistController(id), tabScreen, id, "/artist/viewArtist.fxml",  "View Artist");
        }, editEvent -> {
            int id = Integer.parseInt(((Button) editEvent.getSource()).getUserData().toString());
            editAddScene(new ArtistController(id),tabScreen, id, "/artist/addArtist.fxml", "Edit Artist");
        }, deleteEvent -> {
            int id = Integer.parseInt(((Button) deleteEvent.getSource()).getUserData().toString());
            delete(id, manager);
        }));
    }

    /**
     * Private method for setting buttons in columns for each artwork
     * View, Delete and Edit buttons
     */
    private static void setButtonsInArtworkColumns (TableColumn<Object, Object> actionColumn, TabPane tabScreen, Manager manager) {
        actionColumn.setCellFactory(new TripleButtonCellFactory(viewEvent -> {
            int id = Integer.parseInt(((Button) viewEvent.getSource()).getUserData().toString());
            editAddScene(new ViewArtworkController(id), tabScreen, id, "/artwork/viewArtwork.fxml",  "View Artwork");
        }, editEvent -> {
            int id = Integer.parseInt(((Button) editEvent.getSource()).getUserData().toString());
            editAddScene(new ArtworkController(id),tabScreen, id, "/artwork/addArtwork.fxml", "Edit Artwork");
        }, deleteEvent -> {
            int id = Integer.parseInt(((Button) deleteEvent.getSource()).getUserData().toString());
            delete(id, manager);
        }));
    }

    /**
     * Private method for deleting artist, art style or artwork
     */
    private static void delete(Integer id, Manager manager){
        try {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (!result.get().getButtonData().isCancelButton()){
                manager.delete(id);
            }
        } catch (ArtGalleryException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Private method for editing or adding artist, art style or artwork
     */
    private static void editAddScene(Controller controller, TabPane tabScreen, Integer id, String window, String title) {
        try{
            tabScreen.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(MainEmployeeController.class.getResource(window));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.show();
            stage.setOnHiding(event -> ((Stage)tabScreen.getScene().getWindow()).show());
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Private method for refreshing the window
     */
    static private void refresh(TableView<Object> tableView, ObservableList<Object> list){
        tableView.setItems(list);
        tableView.refresh();
    }

    /**
     * Public method for text field that searches artwork by name, artist or art style
     */
    public void txtFieldActionSearchArtworks(ActionEvent event) throws ArtGalleryException {
        String item = txtFieldSearchArtworks.getText();
        artworks = FXCollections.observableArrayList(artWorkManager.search(item));
        setColumns(colArtworkId,colArtworkName,actionColumnArtwork);
        setButtonsInArtStyleColumns (actionColumnArtwork, tabScreen, artWorkManager);
        refresh(artworkTableView, artworks);
    }

    /**
     * Public method for text field that searches artist by name
     */
    public void txtFieldActionSearchArtist (ActionEvent event) throws ArtGalleryException {
        String search = txtFieldSearchArtist.getText();
        artists = FXCollections.observableArrayList(artistManager.searchByName(search));
        setColumns(colArtistId, colArtistName, actionColumnArtist);
        setButtonsInArtistColumns (actionColumnArtist, tabScreen, artistManager);
        refresh(artistTableView, artists);
    }

    /**
     * Public method for text field that searches art style by name
     */
    public void txtFieldActionSearchArtStyle (ActionEvent event) throws ArtGalleryException {
        String search = txtFieldSearchArtStyle.getText();
        artStyles = FXCollections.observableArrayList(artStyleManager.searchByName(search));
        setColumns(colArtStyleId, colArtStyleName, actionColumnArtStyle);
        setButtonsInArtStyleColumns (actionColumnArtStyle, tabScreen, artStyleManager);
        refresh(artStyleTableView, artStyles);
    }
}