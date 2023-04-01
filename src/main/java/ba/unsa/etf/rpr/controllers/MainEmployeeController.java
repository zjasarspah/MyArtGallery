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
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.*;

/**
 * Controller for main window if you are the employee
 * Employees can view, add or edit the information about artworks, art styles and artists
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
     * @param event on add button pressed
     */
    public void btnActionAddArtist(ActionEvent event){
        (new OpenNewWindow()).openDialog(new ArtistController(null), tabScreen,"/artist/addArtist.fxml", "Add Artist");
    }

    /**
     * Public method for button that adds the art style
     * @param actionEvent on add button pressed
     */
    public void btnActionAddArtStyle(ActionEvent actionEvent) {
        (new OpenNewWindow()).openDialog(new ArtStyleController(null), tabScreen,"/artstyle/addArtStyle.fxml", "Add Art Style");
    }

    /**
     * Public method for button that adds the artwork
     * @param actionEvent on add button pressed
     */
    public void btnActionAddArtWork(ActionEvent actionEvent) {
        (new OpenNewWindow()).openDialog(new ArtworkController (null), tabScreen, "/artwork/addArtwork.fxml", "Add Artwork");
    }

    /**
     * Private method for deleting artist, art style or artwork
     * @param manager to check is it possible to delete artwork, artist or art style
     * @param id to define which artist, artwork or art style should be deleted
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
     * Private method for refreshing the window
     */
    static private void refresh(TableView<Object> tableView, ObservableList<Object> list){
        tableView.setItems(list);
        tableView.refresh();
    }

    /**
     * Public method for refresh button
     * @param list of artists, artworks or art styles
     * @param pathView for the view window
     * @param pathEdit for the edit window
     * @param titleView for the view window
     * @param titleEdit for the edit window
     * @param tableView table with artists, artworks or art styles
     * @param controllerView for view window
     * @param controllerEdit for edit window
     * @param columns in the table with id, name and button for each artist, artwork or art style
     */

    private void refreshButton (ObservableList<Object> list, TableView<Object> tableView, Controller controllerView, Controller controllerEdit, ArrayList<TableColumn<Object, Object>> columns, TabPane tabScreen, String pathView, String titleView, String pathEdit, String titleEdit, Manager manager){
        setColumns(columns.get(0), columns.get(1), columns.get(2));
        setButtonsInColumns (manager, controllerView, controllerEdit, columns.get(2), tabScreen, pathView, titleView, pathEdit, titleEdit);
        refresh(tableView, list);
    }

    /**
     * Public method for button that refreshes list of artworks
     * @param actionEvent on refresh button pressed
     */
    public void btnActionRefreshArtworks(ActionEvent actionEvent) throws ArtGalleryException {
        ArrayList<TableColumn<Object, Object>> columnArtWork = new ArrayList<TableColumn<Object, Object>>() {{
            add(colArtworkId);
            add(colArtworkName);
            add(actionColumnArtwork);
        }};
        artworks = FXCollections.observableArrayList(artWorkManager.getAll());
        refreshButton(artworks,artworkTableView,new ViewArtworkController(), new ArtworkController(),columnArtWork, tabScreen,"/artwork/viewArtwork.fxml",  "View Artwork", "/artwork/addArtwork.fxml", "Edit Artwork", artWorkManager);
    }

    /**
     * Public method for button that refreshes list of artists
     * @param actionEvent on refresh button pressed
     */
    public void btnActionRefreshArtists(ActionEvent actionEvent) throws ArtGalleryException {
        ArrayList<TableColumn<Object, Object>> columnArtist = new ArrayList<TableColumn<Object, Object>>() {{
            add(colArtistId);
            add(colArtistName);
            add(actionColumnArtist);
        }};
        artists = FXCollections.observableArrayList(artistManager.getAll());
        refreshButton(artists,artistTableView,new ViewArtistController(), new ArtistController(), columnArtist, tabScreen, "/artist/viewArtist.fxml", "View Artist", "/artist/addArtist.fxml", "Edit Artist", artistManager);
    }

    /**
     * Public method for button that refresh list of art styles
     * @param actionEvent on refresh button pressed
     */
    public void btnActionRefreshArtStyles(ActionEvent actionEvent) throws ArtGalleryException {
        ArrayList<TableColumn<Object, Object>> columnArtStyle = new ArrayList<TableColumn<Object, Object>>() {{
            add(colArtStyleId);
            add(colArtStyleName);
            add(actionColumnArtStyle);
        }};

        artStyles = FXCollections.observableArrayList(artStyleManager.getAll());
        refreshButton(artStyles,artStyleTableView,new ViewArtStyleController(), new ArtStyleController(), columnArtStyle, tabScreen,"/artstyle/viewArtStyle.fxml",  "View Art Style", "/artstyle/addArtStyle.fxml", "Edit Art Style", artStyleManager);
    }


    /**
     * Private method for setting columns
     * @param columnId id
     * @param columnName name
     * @param actionColumn buttons
     */
    private static void setColumns (TableColumn<Object, Object> columnId, TableColumn<Object, Object> columnName, TableColumn<Object, Object> actionColumn) {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    /**
     * Public method for text field that searches artwork by name, artist or art style
     * @param event enter pressed for searching
     */
    public void txtFieldActionSearchArtworks(ActionEvent event) throws ArtGalleryException {
        ArrayList<TableColumn<Object, Object>> columnArtWork = new ArrayList<TableColumn<Object, Object>>() {{
            add(colArtworkId);
            add(colArtworkName);
            add(actionColumnArtwork);
        }};
        String item = txtFieldSearchArtworks.getText();
        artworks = FXCollections.observableArrayList(artWorkManager.search(item));
        refreshButton(artworks,artworkTableView,new ViewArtworkController(), new ArtworkController(),columnArtWork, tabScreen,"/artwork/viewArtwork.fxml",  "View Artwork", "/artwork/addArtwork.fxml", "Edit Artwork", artWorkManager);
    }

    /**
     * Public method for text field that searches artist by name
     * @param event enter pressed for searching
     */
    public void txtFieldActionSearchArtist (ActionEvent event) throws ArtGalleryException {
        ArrayList<TableColumn<Object, Object>> columnArtist = new ArrayList<TableColumn<Object, Object>>() {{
            add(colArtistId);
            add(colArtistName);
            add(actionColumnArtist);
        }};
        String search = txtFieldSearchArtist.getText();
        artists = FXCollections.observableArrayList(artistManager.searchByName(search));
        refreshButton(artists,artistTableView,new ViewArtistController(), new ArtistController(), columnArtist, tabScreen, "/artist/viewArtist.fxml", "View Artist", "/artist/addArtist.fxml", "Edit Artist", artistManager);
    }

    /**
     * Public method for text field that searches art style by name
     * @param event enter pressed for searching
     */
    public void txtFieldActionSearchArtStyle (ActionEvent event) throws ArtGalleryException {
        ArrayList<TableColumn<Object, Object>> columnArtStyle = new ArrayList<TableColumn<Object, Object>>() {{
            add(colArtStyleId);
            add(colArtStyleName);
            add(actionColumnArtStyle);
        }};
        String search = txtFieldSearchArtStyle.getText();
        artStyles = FXCollections.observableArrayList(artStyleManager.searchByName(search));
        refreshButton(artStyles,artStyleTableView,new ViewArtStyleController(), new ArtStyleController(), columnArtStyle, tabScreen,"/artstyle/viewArtStyle.fxml",  "View ArtStyle", "/artstyle/addArtStyle.fxml", "Edit ArtStyle", artStyleManager);
    }

    /**
     * Private method for setting buttons in columns
     * View, Edit and Delete button
     * @param controllerView for view window
     * @param controllerEdit for edit window
     * @param actionColumn in which column we want to set buttons
     * @param pathView for view window
     * @param titleView for view window
     * @param pathEdit for edit window
     * @param titleEdit for edit window
     * @param manager for checking is it possible to do editing
     */
    private static void setButtonsInColumns (Manager manager, Controller controllerView, Controller controllerEdit, TableColumn<Object, Object> actionColumn, TabPane tabScreen, String pathView, String titleView, String pathEdit, String titleEdit) {
        actionColumn.setCellFactory(new TripleButtonCellFactory<>(viewEvent -> {
            int id = Integer.parseInt(((Button) viewEvent.getSource()).getUserData().toString());
            controllerView.setId(id);
            (new OpenNewWindow()).openDialog(controllerView, tabScreen,  pathView,  titleView);
        }, editEvent -> {
            int id = Integer.parseInt(((Button) editEvent.getSource()).getUserData().toString());
            controllerEdit.setId(id);
            (new OpenNewWindow()).openDialog(controllerEdit,tabScreen, pathEdit, titleEdit);
        }, deleteEvent -> {
            int id = Integer.parseInt(((Button) deleteEvent.getSource()).getUserData().toString());
            delete(id, manager);
        }));
    }
}