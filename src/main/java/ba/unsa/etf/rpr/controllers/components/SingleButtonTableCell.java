package ba.unsa.etf.rpr.controllers.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

/**
 * Custom component for rendering table cell with three buttons (View, Edit and Delete)
 * @author Zerina Jasarspahic
 * @param <T> - Bean class represented in the table cells
 */
public class SingleButtonTableCell<T> extends TableCell<T, T> {

    final private Button view;

    /**
     * Default constructor
     * @param button - event handler for action View
     */
    public SingleButtonTableCell(EventHandler<ActionEvent> button){
        view = new Button("View");
        view.setOnAction(button);
    }

    @Override
    protected void updateItem(T o, boolean b) {
        super.updateItem(o, b);
        if (o != null) {
            HBox box = new HBox();
            box.setAlignment(Pos.CENTER);
            view.setUserData(o);
            box.getChildren().add(view);
            setGraphic(box);
        }
    }
}