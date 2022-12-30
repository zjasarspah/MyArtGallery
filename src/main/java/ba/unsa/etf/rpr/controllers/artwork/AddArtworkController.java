package ba.unsa.etf.rpr.controllers.artwork;

import ba.unsa.etf.rpr.business.ArtStyleManager;
import ba.unsa.etf.rpr.business.ArtWorkManager;
import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.ArtWork;
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

public class AddArtworkController {
    
    @FXML
    public GridPane addArtworkPane;

    @FXML
    public TextField txtFieldName;

    @FXML
    public TextField txtFieldArtistName;

    @FXML
    public TextField txtFieldArtistLifespan;
    @FXML
    public TextField txtFieldArtStyleName;
    @FXML
    public TextField txtFieldArtStyleDuration;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnCancel;

    private final ArtistManager artist = new ArtistManager();
    private final ArtStyleManager artStyle = new ArtStyleManager();
    private final ArtWorkManager artwork = new ArtWorkManager();

    public void btnActionAdd() throws ArtGalleryException {
        Artist a = new Artist();
        a.setId(0);
        a.setName(txtFieldArtistName.getText());
        a.setLifespan(txtFieldArtistLifespan.getText());
        ArtStyle as = new ArtStyle();
        as.setId(0);
        as.setName(txtFieldArtStyleName.getText());
        as.setYear(txtFieldArtStyleDuration.getText());
        ArtWork aw = new ArtWork();
        aw.setName(txtFieldName.getText());
        aw.setArtist(artist.add(a));
        aw.setMovement(artStyle.add(as));
        artwork.add(aw);
        try {
            callWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnActionCancel() {
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
        ((Stage)addArtworkPane.getScene().getWindow()).hide();
    }
}
