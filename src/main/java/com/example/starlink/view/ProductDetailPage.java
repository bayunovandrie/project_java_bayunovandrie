package com.example.starlink.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.starlink.App;
import com.example.starlink.controller.ProductDetailController;
import com.example.starlink.model.Product;

import java.io.IOException;

import static com.example.starlink.utils.UIUtils.show;

public class ProductDetailPage {
    private final Product product;

    public ProductDetailPage(Product product) {
        this.product = product;
    }

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("product-detail-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ProductDetailController productDetailController = fxmlLoader.getController();
        productDetailController.initData(product);

        show(primaryStage, scene);
    }
}