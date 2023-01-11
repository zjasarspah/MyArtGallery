package ba.unsa.etf.rpr.controllers;

/***
 * Abstract class for controllers
 * From this class are extended all controller classes except MainController and LoginController
 * @author Zerina Jasarspahic
 */

public abstract class Controller {
    private Integer id;
    public Controller (Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
