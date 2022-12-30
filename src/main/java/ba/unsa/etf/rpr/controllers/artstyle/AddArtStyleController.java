package ba.unsa.etf.rpr.controllers.artstyle;

import ba.unsa.etf.rpr.business.ArtStyleManager;
import ba.unsa.etf.rpr.domain.ArtStyle;
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

public class AddArtStyleController {

    @FXML
    public GridPane addArtStylePane;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnCancel;
    @FXML
    public TextField txtFieldDuration;
    @FXML
    public TextField txtFieldName;
    private final ArtStyleManager manager = new ArtStyleManager();

    public void btnActionAdd() throws ArtGalleryException {
        ArtStyle a = new ArtStyle();
        a.setName(txtFieldName.getText());
        a.setYear(txtFieldDuration.getText());
        manager.add(a);
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
        ((Stage)addArtStylePane.getScene().getWindow()).hide();
    }
}
