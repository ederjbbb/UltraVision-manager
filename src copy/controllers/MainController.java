package controllers;

import classManagers.ItemsManager;
import view.ViewFactory;
// All controllers extends from this Main controller , this way I can have access to all controllers
public abstract class MainController {

    protected ItemsManager itemsManager;
    protected ViewFactory viewFactory;
    private String fxmlName;


    public MainController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        this.itemsManager = itemsManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }




    public  String getFxmlName(String s){
        return fxmlName;
    }
}
