package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * Main class for working with JavaFX framework
 * @author Zerina Jasarspahic
 */
public class AppFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/artgallerylogin.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle("My Art Gallery");
        stage.setResizable(false);
        stage.show();
    }
}
