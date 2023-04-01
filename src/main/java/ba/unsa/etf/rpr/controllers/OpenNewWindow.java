package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Public class for opening new window
 * @author Zerina Jasarspahic
 */
public class OpenNewWindow {

    /***
     * @param title of the page
     * @param path name of the fxml file
     * @param tabScreen previous view
     * @param controller for new window
     */

    public void openDialog(Controller controller, TabPane tabScreen, String path, String title) {
        try{
            tabScreen.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(MainEmployeeController.class.getResource(path));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.show();
            stage.setOnHiding(event -> ((Stage)tabScreen.getScene().getWindow()).show());
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
