package com.example.starlink.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.starlink.App;

import java.io.IOException;

import static com.example.starlink.utils.UIUtils.show;

public class ProductListPage  {
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("product-list-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        show(primaryStage, scene);
    }
}