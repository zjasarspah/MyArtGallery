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
    public Button btnPassword;
    @FXML
    public Button btnLoginGuest;
    @FXML
    public GridPane loginGridPane;
    @FXML
    public TextField txtFieldUsername;
    @FXML
    public TextField txtFieldPassword;

    /**
     * Method that verifies the given username and password of the employee
     * Main window will open, if the username and the password are correct
     * @param actionEvent on login button pressed
     */
    public void btnActionLogin(ActionEvent actionEvent){
        try {
        Employee e = new Employee();
        e.setUsername(txtFieldUsername.getText());
        e.setPassword(txtFieldPassword.getText());
        (new EmployeeManager()).validateEmployee(e);
            openNewWindow(loginGridPane,"/fxml/main.fxml", "My Art Gallery");
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Method that opens window for changing password of the employee
     * @param actionEvent on login button pressed
     */
    public void btnActionPassword(ActionEvent actionEvent){
        openNewWindow(loginGridPane, "/fxml/changepassword.fxml", "Change Password");
    }

    /**
     * Method that opens main window, if you are not the employee of the art gallery
     * @param actionEvent on login button pressed
     */
    public void btnActionLoginGuest(ActionEvent actionEvent){
        openNewWindow(loginGridPane,"/fxml/mainguest.fxml", "My Art Gallery");
    }

    /**
     * Private method for opening new window
     * @param title of the page
     * @param path name of the fxml file
     * @param pane previous view
     */
    private static void openNewWindow (GridPane pane, String path, String title) {
        try {
            pane.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource(path));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.setTitle(title);
            stage.show();
            if (path.equals("/fxml/changepassword.fxml")) {
                stage.setOnHiding(event -> ((Stage) pane.getScene().getWindow()).show());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong!");
            throw new RuntimeException(e);
        }
    }
}
