package com.example.starlink.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.starlink.model.DaftarPeminjam;
import com.example.starlink.repository.DaftarPeminjamRepository;
import com.example.starlink.view.DaftarPeminjamPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import static com.example.starlink.utils.UIUtils.showAlert;

public class TambahPeminjamController {

    private static final Logger log = Logger.getLogger(TambahPeminjamController.class.getName());
    private static final DaftarPeminjamRepository daftarPeminjamRepository = new DaftarPeminjamRepository();

    @FXML
    private TextField namaPeminjam;
    @FXML
    private TextField judulBuku;
    @FXML
    private TextField tanggalPinjam;
    @FXML
    private TextField tanggalKembali;
    @FXML
    private Button createPeminjamButton;

    @FXML
    public void initialize() {
        tanggalPinjam.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                tanggalPinjam.setText(oldValue);
            }
        });
        tanggalKembali.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                tanggalKembali.setText(oldValue);
            }
        });
    }

    @FXML
    private void handleCreatePeminjam() {
        try {
            String nama = namaPeminjam.getText();
            String judul = judulBuku.getText();
            String tanggalPinjamValue = tanggalPinjam.getText();
            String tanggalKembaliValue = tanggalKembali.getText();

            if (nama.isEmpty() || judul.isEmpty() || tanggalPinjamValue.isEmpty() || tanggalKembaliValue.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Bad Request", "Please enter all the details");
                return;
            }

            LocalDateTime pinjam = LocalDateTime.parse(tanggalPinjamValue, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime kembali = LocalDateTime.parse(tanggalKembaliValue, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            DaftarPeminjam peminjam = new DaftarPeminjam();
            peminjam.setNamaPeminjam(nama);
            peminjam.setJudulBuku(judul);
            peminjam.setTanggalPinjam(pinjam);
            peminjam.setTanggalKembali(kembali);

            daftarPeminjamRepository.save(peminjam);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Successfully added peminjam");
            back((Stage) createPeminjamButton.getScene().getWindow());
        } catch (Exception exception) {
            showAlert(Alert.AlertType.ERROR, "Server Error", "Please contact the administrator");
            log.warning(exception.getMessage());
        }
    }

    @FXML
    private void handleBack() {
        Stage primaryStage = (Stage) createPeminjamButton.getScene().getWindow();
        back(primaryStage);
    }

    public void back(Stage stage) {
        DaftarPeminjamPage daftarPeminjamPage = new DaftarPeminjamPage();
        try {
            daftarPeminjamPage.start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
