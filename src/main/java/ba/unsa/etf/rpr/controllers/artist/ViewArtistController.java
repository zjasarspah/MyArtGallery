package ba.unsa.etf.rpr.controllers.artist;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.controllers.Controller;
import ba.unsa.etf.rpr.controllers.model.ArtistModel;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Controller for View window - Artist
 * @author Zerina Jasarspahic
 */
public class ViewArtistController extends Controller {

    @FXML
    public GridPane artistPane;
    @FXML
    public Label labelName;
    @FXML
    public Label labelLifespan;
    @FXML
    public Button btnOk;
    private final ArtistModel model = new ArtistModel();
    private final ArtistManager artistManager = new ArtistManager();
    public ViewArtistController (Integer id){
        super(id);
    }
    public void btnActionOk (ActionEvent actionEvent) {
        artistPane.getScene().getWindow().hide();
    }

    @FXML
    public void initialize() {
        try{
            labelName.textProperty().bindBidirectional(model.name);
            labelLifespan.textProperty().bindBidirectional(model.lifespan);
            if (getId() != null) {
                model.fromArtist(artistManager.getById(getId()));
            }
        } catch (ArtGalleryException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}