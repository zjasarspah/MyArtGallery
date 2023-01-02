package ba.unsa.etf.rpr.controllers.artwork;

import ba.unsa.etf.rpr.business.ArtWorkManager;
import ba.unsa.etf.rpr.controllers.Controller;
import ba.unsa.etf.rpr.controllers.model.ArtworkModel;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Controller for View window - Artwork
 * @author Zerina Jasarspahic
 */

public class ViewArtworkController extends Controller {

    @FXML
    public GridPane artworkPane;
    @FXML
    public Label labelName;
    @FXML
    public Label labelArtist;
    @FXML
    public Label labelArtStyle;

    @FXML
    public Button btnOk;

    private final ArtworkModel model = new ArtworkModel();
    private final ArtWorkManager artWorkManager = new ArtWorkManager();
    public ViewArtworkController (Integer id){
        super(id);
    }

    public void btnActionOk (ActionEvent actionEvent) {
        artworkPane.getScene().getWindow().hide();
    }

    @FXML
    public void initialize() {
        try{
            labelName.textProperty().bindBidirectional(model.name);
            labelArtist.textProperty().bindBidirectional(model.artistName);
            labelArtStyle.textProperty().bindBidirectional(model.artStyleName);
            if (getId() != null) {
                model.viewFromArtwork(artWorkManager.getById(getId()));
            }
        } catch (ArtGalleryException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}