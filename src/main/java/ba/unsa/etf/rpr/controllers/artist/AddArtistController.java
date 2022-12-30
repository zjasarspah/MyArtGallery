package ba.unsa.etf.rpr.controllers.artist;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    public TextField txtfieldName;

    @FXML
    public TextField txtfieldLifespan;

    private ArtistManager manager = new ArtistManager();
    @FXML
    void initialize() {

    }
    public void btnActionAdd(ActionEvent actionEvent) throws ArtGalleryException, IOException {
        try {
        Artist a = new Artist();
        a.setName(txtfieldName.getText());
        a.setLifespan(txtfieldLifespan.getText());
        manager.add(a);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(true);
        stage.setTitle("My Art Gallery");
        stage.show();
        ((Stage)addArtistPane.getScene().getWindow()).hide();
        } catch (IOException e) {
            System.out.println("Something went wrong with opening new ArtistSearch window!");
            throw new RuntimeException(e);
        }
    }
}
