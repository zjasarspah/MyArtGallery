package ba.unsa.etf.rpr.controllers.artwork;

import ba.unsa.etf.rpr.business.ArtStyleManager;
import ba.unsa.etf.rpr.business.ArtWorkManager;
import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.controllers.Controller;
import ba.unsa.etf.rpr.controllers.MainEmployeeController;
import ba.unsa.etf.rpr.controllers.artist.ArtistController;
import ba.unsa.etf.rpr.controllers.artstyle.ArtStyleController;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.ArtWork;
import ba.unsa.etf.rpr.controllers.model.ArtworkModel;
import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Controller for Add and Edit window - Artwork
 * @author Zerina Jasarspahic
 */
public class ArtworkController extends Controller {

    @FXML
    public GridPane artworkPane;
    @FXML
    public TextField txtFieldName;
    @FXML
    public ComboBox<Artist> artistId;
    @FXML
    public ComboBox<ArtStyle> artStyleId;
    @FXML
    public Button btnArtist;
    @FXML
    public Button btnArtStyle;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnCancel;
    private final ArtworkModel model = new ArtworkModel();
    private final ArtWorkManager artWorkManager = new ArtWorkManager();
    private final ArtistManager artistManager = new ArtistManager();
    private final ArtStyleManager artStyleManager = new ArtStyleManager();
    public ArtworkController (Integer id){
        super(id);
    }

    public ArtworkController() {
    }
    @FXML
    public void initialize() {
        try{
            artistId.setItems(FXCollections.observableList(artistManager.getAll()));
            artStyleId.setItems(FXCollections.observableList(artStyleManager.getAll()));
            txtFieldName.textProperty().bindBidirectional(model.name);
            artistId.valueProperty().bindBidirectional(model.artist);
            artStyleId.valueProperty().bindBidirectional(model.artStyle);
            if (getId() != null) {
                model.fromArtwork(artWorkManager.getById(getId()));
            }
        } catch (ArtGalleryException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void btnActionAdd(ActionEvent actionEvent) {
        try{
            ArtWork q = model.toArtwork();
            if (getId() != null){
                q.setId(getId());
                artWorkManager.update(q);
            }else{
                artWorkManager.add(q);
            }
            artworkPane.getScene().getWindow().hide();
        }catch (ArtGalleryException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void btnActionCancel(ActionEvent actionEvent) {
        artworkPane.getScene().getWindow().hide();
    }

    /***
     * Public method for adding Artist if he/she doesn't exist in the base
     */
    public void btnActionArtist (ActionEvent actionEvent) {
        add(new ArtistController(null), artworkPane, "/artist/addArtist.fxml", "Add Artist");
    }

    /***
     * Public method for adding Art Style if it doesn't exist in the base
     */
    public void btnActionArtStyle (ActionEvent actionEvent) {
        add(new ArtStyleController(null), artworkPane,"/artstyle/addArtStyle.fxml", "Add Art Style");
    }

    private static void add(Controller controller, GridPane gridPane, String window, String title) {
        try{
            gridPane.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(MainEmployeeController.class.getResource(window));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.show();
            stage.setOnHiding(event -> ((Stage)gridPane.getScene().getWindow()).show());
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}