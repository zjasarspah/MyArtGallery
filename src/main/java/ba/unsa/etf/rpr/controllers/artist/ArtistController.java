package ba.unsa.etf.rpr.controllers.artist;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.controllers.Controller;
import ba.unsa.etf.rpr.controllers.model.ArtistModel;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/***
        * Controller for Add and Edit window - Artist
        * @author Zerina Jasarspahic
        */

public class ArtistController extends Controller {
    @FXML
    public GridPane artistPane;
    @FXML
    public TextField txtFieldName;
    @FXML
    public TextField txtFieldLifespan;
    @FXML
    public TextField txtFieldNationality;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnCancel;
    private final ArtistModel model = new ArtistModel();
    private final ArtistManager manager = new ArtistManager();

    public ArtistController (Integer editId){
        super(editId);
    }

    @FXML
    public void initialize() {
        try{
            txtFieldName.textProperty().bindBidirectional(model.name);
            txtFieldLifespan.textProperty().bindBidirectional(model.lifespan);
            txtFieldNationality.textProperty().bindBidirectional(model.nationality);
            if (getId() != null) {
                model.fromArtist(manager.getById(getId()));
            }
        } catch (ArtGalleryException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void btnActionAdd(ActionEvent actionEvent) {
        try {
            Artist q = model.toArtist();
            if (getId() != null){
                q.setId(getId());
                manager.update(q);
            }else{
                manager.add(q);
            }
            artistPane.getScene().getWindow().hide();
        } catch (ArtGalleryException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void btnActionCancel(ActionEvent actionEvent) {
        artistPane.getScene().getWindow().hide();
    }
}