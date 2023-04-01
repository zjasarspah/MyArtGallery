package ba.unsa.etf.rpr.controllers.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Single button cell factory for creation of View button for each cell in the table
 * @author Zerina Jasarspahic
 * @param <T>
 */
public class SingleButtonCellFactory<T> implements Callback<TableColumn<T, T>, TableCell<T, T>> {

    private final EventHandler<ActionEvent> button;

    /**
     * @param button - event handler for button View
     */
    public SingleButtonCellFactory(EventHandler<ActionEvent> button) {
        this.button = button;
    }

    @Override
    public TableCell<T, T> call(TableColumn<T, T> objectTableColumn) {
        return new SingleButtonTableCell<>(button);
    }
}