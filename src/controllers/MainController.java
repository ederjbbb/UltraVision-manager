package controllers;

import classManager.Validations;
import view.ViewFactory;
// All controllers extends from this Main controller , this way I can have access to all controllers
public abstract class MainController {

    protected Validations validations;
    protected ViewFactory viewFactory;
    private String fxmlName;



    public MainController(Validations validations, ViewFactory viewFactory, String fxmlName) {
        this.validations = validations;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public MainController() {

    }


    public  String getFxmlName()
    {
        return fxmlName;
    }

}
