package com.example.starlink.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.starlink.App;
import com.example.starlink.model.DaftarPeminjam;

import java.io.IOException;

import static com.example.starlink.utils.UIUtils.show;

public class PeminjamDetailPage {
    private final DaftarPeminjam peminjam;

    public PeminjamDetailPage(DaftarPeminjam peminjam) {
        this.peminjam = peminjam;
    }

//    public void start(Stage primaryStage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("peminjam-detail-page.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//
//        PeminjamDetailController peminjamDetailController = fxmlLoader.getController();
//        peminjamDetailController.initData(peminjam);
//
//        show(primaryStage, scene);
//    }
}
