package ba.unsa.etf.rpr.controllers.artstyle;

import ba.unsa.etf.rpr.business.ArtStyleManager;
import ba.unsa.etf.rpr.controllers.Controller;
import ba.unsa.etf.rpr.controllers.model.ArtStyleModel;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Controller for View window - ArtStyle
 * @author Zerina Jasarspahic
 */
public class ViewArtStyleController extends Controller {

    @FXML
    public GridPane artStylePane;
    @FXML
    public Label labelName;
    @FXML
    public Label labelDuration;
    @FXML
    public Button btnOk;
    private final ArtStyleModel model = new ArtStyleModel();
    private final ArtStyleManager artWorkManager = new ArtStyleManager();

    public ViewArtStyleController() {

    }

    @FXML
    public void initialize() {
        try{
            labelName.textProperty().bindBidirectional(model.name);
            labelDuration.textProperty().bindBidirectional(model.duration);
            if (getId() != null) {
                model.fromArtStyle(artWorkManager.getById(getId()));
            }
        } catch (ArtGalleryException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Method that exits the current window
     * @param actionEvent exit button pressed
     */
    public void btnActionOk (ActionEvent actionEvent) {
        artStylePane.getScene().getWindow().hide();
    }
}