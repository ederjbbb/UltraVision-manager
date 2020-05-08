module UltraVision {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires javafx.base;
    requires activation;
    requires java.mail;

    requires java.sql;
    requires java.desktop;


    opens view.images;
    opens images;
    opens sample;
    opens controllers;
    opens models;
    opens styles;
    opens classManager;
    opens view;
}