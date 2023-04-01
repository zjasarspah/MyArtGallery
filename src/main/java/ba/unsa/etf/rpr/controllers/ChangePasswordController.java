package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Controller for changing password
 * @author Zerina Jasarspahic
 */

public class ChangePasswordController {

    @FXML
    public Button btnSave;
    @FXML
    public Button btnCancel;
    @FXML
    public GridPane changePasswordGridPane;
    @FXML
    public TextField txtFieldUsername;
    @FXML
    public TextField txtFieldCurrentPassword;
    @FXML
    public TextField txtFieldNewPassword;
    @FXML
    public TextField txtFieldVerifyPassword;

    /**
     * Method that verifies the given username and password of the employee
     * For changing the password, the employee must be in the art gallery data base
     * For changing password, tne new password and the verified password must be the same
     * @param actionEvent save button presses
     * @throws Exception
     */
    public void btnActionSave(ActionEvent actionEvent){
        try {
            Employee e = new Employee();
            String newPassword = txtFieldNewPassword.getText();
            String verifyPassword = txtFieldVerifyPassword.getText();
            e.setUsername(txtFieldUsername.getText());
            e.setPassword(txtFieldCurrentPassword.getText());
            (new EmployeeManager()).verifyPassword(e, newPassword, verifyPassword);
            changePasswordGridPane.getScene().getWindow().hide();
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Method that exits the current window without saving any changes to the database
     * @param actionEvent exit button pressed
     */
    public void btnActionCancel(ActionEvent actionEvent){changePasswordGridPane.getScene().getWindow().hide();}
}
