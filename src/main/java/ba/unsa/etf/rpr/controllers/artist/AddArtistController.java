package ba.unsa.etf.rpr.controllers.artist;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AddArtistController {

    @FXML
    public GridPane addArtistPane;
    @FXML
    public Button btnAdd;
    @FXML
    public TextField txtFieldName;

    @FXML
    public TextField txtFieldLifespan;

    @FXML
    public Button btnCancel;

    private final ArtistManager manager = new ArtistManager();
    @FXML
    void initialize() {
    }

    public void btnActionAdd() throws ArtGalleryException {
        Artist a = new Artist();
        a.setName(txtFieldName.getText());
        a.setLifespan(txtFieldLifespan.getText());
        manager.add(a);
        try {
            callWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnActionCancel () {
            try {
                callWindow();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    private void callWindow () throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(true);
        stage.setTitle("My Art Gallery");
        stage.show();
        ((Stage)addArtistPane.getScene().getWindow()).hide();
    }
}
