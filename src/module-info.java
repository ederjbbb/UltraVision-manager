module DashCA {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;



    opens view.images;
    opens images;
    opens sample;
    opens controllers;
    opens models;
    opens styles;
    opens classManagers;
    opens view;
}