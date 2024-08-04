package com.example.starlink.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import com.example.starlink.config.SessionManager;
import com.example.starlink.model.DaftarPeminjam;
import com.example.starlink.repository.DaftarPeminjamRepository;
import com.example.starlink.view.TambahPeminjamPage;
import com.example.starlink.view.LoginPage;
import com.example.starlink.view.PeminjamDetailPage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class DaftarPeminjamController {

    private static final Logger log = Logger.getLogger(DaftarPeminjamController.class.getName());
    private final DaftarPeminjamRepository daftarPeminjamRepository = new DaftarPeminjamRepository();

    @FXML
    private TableView<DaftarPeminjam> daftarPeminjamTableView;
    @FXML
    private TableColumn<DaftarPeminjam, String> judulBukuColumn;
    @FXML
    private TableColumn<DaftarPeminjam, String> namaPeminjamColumn;
    @FXML
    private TableColumn<DaftarPeminjam, LocalDateTime> tanggalPinjamColumn;
    @FXML
    private TableColumn<DaftarPeminjam, LocalDateTime> tanggalKembaliColumn;
    @FXML
    private Button tambahPeminjamButton;
    @FXML
    private Button logoutButton;

    @FXML
    public void initialize() throws IOException {
        daftarPeminjamTableView.setItems(getDaftarPeminjam());

        judulBukuColumn.setCellValueFactory(new PropertyValueFactory<>("judulBuku"));
        namaPeminjamColumn.setCellValueFactory(new PropertyValueFactory<>("namaPeminjam"));
        tanggalPinjamColumn.setCellValueFactory(new PropertyValueFactory<>("tanggalPinjam"));
        tanggalKembaliColumn.setCellValueFactory(new PropertyValueFactory<>("tanggalKembali"));

//        daftarPeminjamTableView.setRowFactory(tv -> {
//            TableRow<DaftarPeminjam> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
//                    DaftarPeminjam clickedPeminjam = row.getItem();
//                    log.info("Peminjam clicked: " + clickedPeminjam);
//                    showPeminjamDetailPage((Stage) daftarPeminjamTableView.getScene().getWindow(), clickedPeminjam);
//                }
//            });
//            return row;
//        });
    }

    @FXML
    private void handleTambahPeminjam() throws IOException {
        new TambahPeminjamPage().start((Stage) tambahPeminjamButton.getScene().getWindow());
    }

    @FXML
    private void handleLogout() throws IOException {
        SessionManager.clearSession();
        new LoginPage().start((Stage) logoutButton.getScene().getWindow());
    }

    private ObservableList<DaftarPeminjam> getDaftarPeminjam() {
        List<DaftarPeminjam> daftarPeminjam = daftarPeminjamRepository.findAll();
        return FXCollections.observableList(daftarPeminjam);
    }

//    private void showPeminjamDetailPage(Stage primaryStage, DaftarPeminjam peminjam) {
//        PeminjamDetailPage detailPage = new PeminjamDetailPage(peminjam);
//        try {
//            detailPage.start(primaryStage);
//        } catch (Exception e) {
//            log.warning("Error while showing peminjam detail page");
//        }
//    }
}
