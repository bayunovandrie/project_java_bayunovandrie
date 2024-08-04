package com.example.starlink;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.starlink.config.SessionManager;

import java.io.IOException;

import static com.example.starlink.utils.UIUtils.show;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        show(stage, scene);
    }

    public static void main(String[] args) {
        launch();
    }
}