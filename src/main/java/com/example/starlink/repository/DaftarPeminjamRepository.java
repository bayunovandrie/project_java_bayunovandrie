package com.example.starlink.repository;

import com.example.starlink.config.DatabaseConnection;
import com.example.starlink.model.DaftarPeminjam;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaftarPeminjamRepository {
    private static final Logger log = Logger.getLogger(DaftarPeminjamRepository.class.getName());

    public void save(DaftarPeminjam peminjam) {
        String sql = "INSERT INTO daftar_peminjam (nama_peminjam, judul_buku, tanggal_pinjam, tanggal_kembali) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getDbConnection();
             PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, peminjam.getNamaPeminjam());
            statement.setString(2, peminjam.getJudulBuku());
            statement.setTimestamp(3, Timestamp.valueOf(peminjam.getTanggalPinjam()));
            statement.setTimestamp(4, Timestamp.valueOf(peminjam.getTanggalKembali()));
            statement.executeUpdate();

        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error saving peminjam: {0}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public DaftarPeminjam findById(int id) {
        String sql = "SELECT id, nama_peminjam, judul_buku, tanggal_pinjam, tanggal_kembali FROM daftar_peminjam WHERE id = ?";
        DaftarPeminjam peminjam = null;
        try (Connection connection = DatabaseConnection.getDbConnection();
             PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    peminjam = mapResultSetToPeminjam(rs);
                }
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error finding peminjam by ID: {0}", e.getMessage());
        }
        return peminjam;
    }

    public List<DaftarPeminjam> findAll() {
        String sql = "SELECT id, nama_peminjam, judul_buku, tanggal_pinjam, tanggal_kembali FROM daftar_peminjam";
        List<DaftarPeminjam> peminjams = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getDbConnection();
             Statement statement = Objects.requireNonNull(connection).createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                peminjams.add(mapResultSetToPeminjam(rs));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error finding all peminjams: {0}", e.getMessage());
            throw new RuntimeException(e);
        }
        return peminjams;
    }

    public boolean update(DaftarPeminjam peminjam) {
        String sql = "UPDATE daftar_peminjam SET nama_peminjam = ?, judul_buku = ?, tanggal_pinjam = ?, tanggal_kembali = ? WHERE id = ?";
        boolean updated = false;
        try (Connection connection = DatabaseConnection.getDbConnection();
             PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(sql)) {
            statement.setString(1, peminjam.getNamaPeminjam());
            statement.setString(2, peminjam.getJudulBuku());
            statement.setTimestamp(3, Timestamp.valueOf(peminjam.getTanggalPinjam()));
            statement.setTimestamp(4, Timestamp.valueOf(peminjam.getTanggalKembali()));
            statement.setInt(5, peminjam.getId());
            updated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error updating peminjam: {0}", e.getMessage());
        }
        return updated;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM daftar_peminjam WHERE id = ?";
        boolean deleted = false;
        try (Connection connection = DatabaseConnection.getDbConnection();
             PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(sql)) {
            statement.setInt(1, id);
            deleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error deleting peminjam: {0}", e.getMessage());
        }
        return deleted;
    }

    private DaftarPeminjam mapResultSetToPeminjam(ResultSet rs) throws SQLException {
        DaftarPeminjam peminjam = new DaftarPeminjam();
        peminjam.setId(rs.getInt("id"));
        peminjam.setNamaPeminjam(rs.getString("nama_peminjam"));
        peminjam.setJudulBuku(rs.getString("judul_buku"));
        peminjam.setTanggalPinjam(rs.getTimestamp("tanggal_pinjam").toLocalDateTime());
        peminjam.setTanggalKembali(rs.getTimestamp("tanggal_kembali").toLocalDateTime());
        return peminjam;
    }
}
