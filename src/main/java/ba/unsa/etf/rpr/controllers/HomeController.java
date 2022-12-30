package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.ArtistDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Artist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class HomeController {

    public Button btnSpasi;
    public ListView<String> lvArtists;
    private ObservableList<String> artistList;
//    private ArtistDaoSQLImpl artistDaoSQL = new ArtistDaoSQLImpl();

    public HomeController() {
        lvArtists =  new ListView<String>();
        artistList = FXCollections.observableArrayList("Meho", "Kum", "Ja");
        System.out.println(artistList.size());
    }

    @FXML
    void initialize() {
        System.out.println(artistList.size());
        lvArtists.setItems(artistList);
        lvArtists.getSelectionModel().selectedItemProperty().addListener((obs, lastItem, newItem) -> {
            System.out.println("Zadnji kliknuti" + lastItem);
            System.out.println("Trenutni kliknuti" + newItem);
        });
    }

    public void btnActionSpasi(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ArtistSearch.fxml"));
            Parent root = null;
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println("Something went wrong with opening new ArtistSearch window!");
            throw new RuntimeException(e);
        }

    }
}
