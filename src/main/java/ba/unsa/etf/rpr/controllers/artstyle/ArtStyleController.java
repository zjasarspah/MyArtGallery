package ba.unsa.etf.rpr.controllers.artstyle;

import ba.unsa.etf.rpr.business.ArtStyleManager;
import ba.unsa.etf.rpr.controllers.Controller;
import ba.unsa.etf.rpr.controllers.model.ArtStyleModel;
import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/***
        * Controller for Add and Edit window - ArtStyle
        * @author Zerina Jasarspahic
        */
public class ArtStyleController extends Controller {
    @FXML
    public GridPane artStylePane;
    @FXML
    public TextField txtFieldName;
    @FXML
    public TextField txtFieldDuration;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnCancel;
    private final ArtStyleModel model = new ArtStyleModel();
    private final ArtStyleManager manager = new ArtStyleManager();

    public ArtStyleController (Integer id){
        super(id);
    }

    public ArtStyleController() {

    }

    @FXML
    public void initialize() {
        try{
            txtFieldName.textProperty().bindBidirectional(model.name);
            txtFieldDuration.textProperty().bindBidirectional(model.duration);
            if (getId() != null) {
                model.fromArtStyle(manager.getById(getId()));
            }
        } catch (ArtGalleryException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Method for adding/editing art style in the database
     * @param actionEvent on add/edit button pressed
     */
    public void btnActionAdd(ActionEvent actionEvent) {
        try{
            ArtStyle q = model.toArtStyle();
            if (getId() != null){
                q.setId(getId());
                manager.update(q);
            }else{
                manager.add(q);
            }
            artStylePane.getScene().getWindow().hide();
        }catch (ArtGalleryException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Method that exits the current window without adding/editing an art style
     * @param actionEvent exit button pressed
     */
    public void btnActionCancel(ActionEvent actionEvent) {
        artStylePane.getScene().getWindow().hide();
    }
}