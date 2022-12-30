package ba.unsa.etf.rpr.controllers.components;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.business.Manager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainComponents {

    public void initialize (TabPane tabScreen, TableView<Object> tableView, TableColumn<Object, Object> columnId, TableColumn<Object, Object> columnName, TableColumn<Object, Object> actionColumn, ObservableList<Object> list, Manager manager, String view, String edit) throws ArtGalleryException {
        list = FXCollections.observableArrayList(manager.getAll());
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ObservableList<Object> finalList = list;
        actionColumn.setCellFactory(new TripleButtonCellFactory(viewEvent -> {
            int artistId = Integer.parseInt(((Button) viewEvent.getSource()).getUserData().toString());
            artistScene(tabScreen, artistId, view, "View Artist", tableView, finalList);
        }, editEvent -> {
            int artistId = Integer.parseInt(((Button) editEvent.getSource()).getUserData().toString());
            artistScene(tabScreen, artistId, edit, "Edit Artist",  tableView, finalList);
        }, deleteEvent -> {
            int artistId = Integer.parseInt(((Button) deleteEvent.getSource()).getUserData().toString());
            deleteArtist(artistId,  tableView, finalList);
        }));

        refresh(tableView, finalList);
    }

    public void deleteArtist(Integer artistId, TableView<Object> tableView, ObservableList<Object> list){
        try {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (!result.get().getButtonData().isCancelButton()){
                DaoFactory.artistDao().delete(artistId);
                refresh(tableView, list);
            }
        } catch (ArtGalleryException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    private void artistScene(TabPane tabScreen, int artistId, String window, String title, TableView<Object> tableView, ObservableList<Object> list) {
        try{
            ((Stage)tabScreen.getScene().getWindow()).hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(window));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.show();
            stage.setOnHiding(event -> {
                ((Stage)tabScreen.getScene().getWindow()).show();
                refresh(tableView, list);
            });
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    static private void refresh(TableView<Object> tableView, ObservableList<Object> list){
        tableView.setItems(list);
        tableView.refresh();
    }
}
