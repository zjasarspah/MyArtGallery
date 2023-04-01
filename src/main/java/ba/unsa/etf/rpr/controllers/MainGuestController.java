package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ArtStyleManager;
import ba.unsa.etf.rpr.business.ArtWorkManager;
import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.controllers.artist.ViewArtistController;
import ba.unsa.etf.rpr.controllers.artstyle.ViewArtStyleController;
import ba.unsa.etf.rpr.controllers.artwork.ViewArtworkController;
import ba.unsa.etf.rpr.controllers.components.SingleButtonCellFactory;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * Controller for main window if you are a guest
 * Guests can only view the information about artworks, art styles and artists
 * @author Zerina Jasarspahic
 */
public class MainGuestController {

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
     * Public method for refresh button
     * @param list of artists, artworks or art styles
     * @param path for the view window
     * @param title for the view window
     * @param tableView table with artists, artworks or art styles
     * @param controller for view window
     * @param columns in the table with id, name and button for each artist, artwork or art style
     */
    private void refreshButton (ObservableList<Object> list, TableView<Object> tableView, Controller controller, ArrayList<TableColumn<Object, Object>> columns, String path, String title){
        setColumns(columns.get(0), columns.get(1), columns.get(2));
        setButtonsInColumns (controller,columns.get(2), tabScreen, path, title);
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
        refreshButton(artworks,artworkTableView,new ViewArtworkController(),columnArtWork, "/artwork/viewArtwork.fxml",  "View Artwork");
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
        refreshButton(artists,artistTableView,new ViewArtistController(),columnArtist, "/artist/viewArtist.fxml", "View Artist");
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
        refreshButton(artStyles,artStyleTableView,new ViewArtStyleController(),columnArtStyle, "/artstyle/viewArtStyle.fxml",  "View ArtStyle");
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
     * Private method for refreshing the window
     */
    static private void refresh(TableView<Object> tableView, ObservableList<Object> list){
        tableView.setItems(list);
        tableView.refresh();
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
        refreshButton(artworks,artworkTableView,new ViewArtworkController(),columnArtWork, "/artwork/viewArtwork.fxml",  "View Artwork");
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
        refreshButton(artists,artistTableView,new ViewArtistController(),columnArtist, "/artist/viewArtist.fxml", "View Artist");
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
        refreshButton(artStyles,artStyleTableView,new ViewArtStyleController(),columnArtStyle, "/artstyle/viewArtStyle.fxml",  "View ArtStyle");
    }

    /**
     * Private method for setting buttons in columns
     * Only View button
     * @param controller for view window
     * @param actionColumn in which column we want to set buttons
     * @param path for view window
     * @param title for view window
     */
    private static void setButtonsInColumns (Controller controller, TableColumn<Object, Object> actionColumn, TabPane tabScreen, String path, String title) {
        actionColumn.setCellFactory(new SingleButtonCellFactory<>(viewEvent -> {
            int id = Integer.parseInt(((Button) viewEvent.getSource()).getUserData().toString());
            controller.setId(id);
            (new OpenNewWindow()).openDialog(controller, tabScreen, path,  title);
        }));
    }

}
