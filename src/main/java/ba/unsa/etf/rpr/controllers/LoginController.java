package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
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

/**
 * Controller for login
 * @author Zerina Jasarspahic
 */

public class LoginController {

    @FXML
    public Button btnLogin;
    @FXML
    public GridPane loginGridPane;
    @FXML
    public TextField txtFieldUsername;
    @FXML
    public TextField txtFieldPassword;
    public void btnActionLogin(ActionEvent actionEvent){
        try {
        Employee e = new Employee();
        e.setUsername(txtFieldUsername.getText());
        e.setPassword(txtFieldPassword.getText());
        (new EmployeeManager()).isValid(e);
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Private method for opening main window
     * @author Zerina Jasarspahic
     */
    private static void openMainWindow (GridPane gridPane) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/fxml/main.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.setTitle("My Art Gallery");
            stage.show();
            gridPane.getScene().getWindow().hide();
        } catch (IOException e) {
            System.out.println("Something went wrong with opening new ArtistSearch window!");
            throw new RuntimeException(e);
        }

    }
}
