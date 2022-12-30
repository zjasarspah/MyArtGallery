package ba.unsa.etf.rpr.controllers.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Triple button cell factory for creation of buttons for each cell in the table
 * @param <T>
 */
public class TripleButtonCellFactory<T> implements Callback<TableColumn<T, T>, TableCell<T, T>> {

    private final EventHandler<ActionEvent> buttonOne;
    private final EventHandler<ActionEvent> buttonTwo;
    private final EventHandler<ActionEvent> buttonThree;

    /**
     *
     * @param buttonOne - event handler for first button (View)
     * @param buttonTwo - event handler for second button (Edit)
     * @param buttonThree - event handler for third button (Delete)
     */
    public TripleButtonCellFactory(EventHandler<ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo, EventHandler<ActionEvent> buttonThree){
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
        this.buttonThree = buttonThree;
    }

    @Override
    //vracam celiju u kojoj su napravljena dugmad, kao parametar se salje kolona u kojoj je potrebno napraviti celiju
    //sa odredenim eventima, u ovom slucaju dugmadima
    public TableCell<T, T> call(TableColumn<T, T> objectTableColumn) {
        return new TripleButtonTableCell<>(buttonOne, buttonTwo, buttonThree);
    }
}