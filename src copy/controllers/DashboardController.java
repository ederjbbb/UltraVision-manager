package controllers;

import classManagers.ItemsManager;
import view.ViewFactory;

public class DashboardController extends MainController{
    public DashboardController(ItemsManager itemsManager, ViewFactory viewFactory, String s) {
        super(itemsManager, viewFactory, s);
    }

    public String getFxmlName(String s) {
        return "DashboardView.fxml";
    }
}
