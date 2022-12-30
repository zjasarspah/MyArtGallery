package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LoginController {

    public Button btnLogin;
    @FXML
    public GridPane loginGridPane;
    @FXML
    void initialize() {
    }

    public void btnActionLogin(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(true);
            stage.setTitle("My Art Gallery");
            stage.show();
            ((Stage)loginGridPane.getScene().getWindow()).hide();
            stage.setOnHiding(event -> {
                ((Stage)loginGridPane.getScene().getWindow()).show();
            });
        } catch (IOException e) {
            System.out.println("Something went wrong with opening new ArtistSearch window!");
            throw new RuntimeException(e);
        }
    }
}
