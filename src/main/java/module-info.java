module com.example.starlink {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires jbcrypt;

    opens com.example.starlink to javafx.fxml;
    exports com.example.starlink;
    exports com.example.starlink.controller;
    exports com.example.starlink.repository;
    exports com.example.starlink.model;
    opens com.example.starlink.controller to javafx.fxml;
}