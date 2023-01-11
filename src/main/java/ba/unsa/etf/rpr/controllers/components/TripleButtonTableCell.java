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
public class TripleButtonTableCell<T> extends TableCell<T, T> {

    final private Button view;
    final private Button edit;
    final private Button delete;

    /**
     * Default constructor
     * @param buttonOne - event handler for action on first button (View)
     * @param buttonTwo - event handler for action on second button (Edit)
     * @param buttonThree - event handler for action on third button (Delete)
     */
    public TripleButtonTableCell(EventHandler<ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo, EventHandler<ActionEvent> buttonThree){
        view = new Button("View");
        view.setOnAction(buttonOne);
        edit = new Button("Edit");
        edit.setOnAction(buttonTwo);
        delete = new Button ("Delete");
        delete.setOnAction(buttonThree);
    }

    @Override
    protected void updateItem(T o, boolean b) {
        super.updateItem(o, b);
        if (o != null) {
            HBox box = new HBox();
            box.setAlignment(Pos.CENTER);
            view.setUserData(o);
            edit.setUserData(o);
            delete.setUserData(o);
            box.getChildren().add(view);
            box.getChildren().add(edit);
            box.getChildren().add(delete);
            setGraphic(box);
        }
    }
}