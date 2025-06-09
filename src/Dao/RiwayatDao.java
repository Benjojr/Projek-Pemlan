/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Entity.Riwayat;
import Entity.RiwayatTopUp;
import Entity.RiwayatTransfer;
import Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RiwayatDao {

    public static void simpanRiwayatTransfer(Connection conn, RiwayatTransfer riwayat) throws SQLException {
        String query = """
                INSERT INTO RiwayatTransfer(noRekPengirim, noRekPenerima, bankPengirim, bankPenerima, tanggal, waktu, Nominal)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, riwayat.getNoRekPengirim());
        pst.setString(2, riwayat.getNoRekPenerima());
        pst.setString(3, riwayat.getBankPengirim());
        pst.setString(4, riwayat.getBankPenerima());
        pst.setObject(5, riwayat.getTanggal());
        pst.setObject(6, riwayat.getWaktu());
        pst.setDouble(7, riwayat.getNominal());
        pst.executeUpdate();
    }

    public static void simpanRiwayatTopUp(Connection conn, RiwayatTopUp riwayat, String noHpPenerima, String jenisEWallet) throws SQLException {
        String query = """
                INSERT INTO RiwayatTopUp(noRekPengirim, noHpPenerima, jenisEWallet, tanggal, waktu, Nominal)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, riwayat.getNoRekPengirim());
        pst.setString(2, noHpPenerima);
        pst.setString(3, jenisEWallet);
        pst.setObject(4, riwayat.getTanggal());
        pst.setObject(5, riwayat.getWaktu());
        pst.setDouble(6, riwayat.getNominal());
        pst.executeUpdate();
    }

    public static List<Riwayat> getSemuaRiwayatByNoRek(String noRek) throws SQLException {
        List<Riwayat> list = new ArrayList<>();

        // Ambil dari RiwayatTransfer (sebagai pengirim atau penerima)
        String q1 = """
            SELECT DISTINCT t.*, nPengirim.nama AS namaPengirim, nPenerima.nama AS namaPenerima
            FROM RiwayatTransfer t
            LEFT JOIN Nasabah nPengirim ON t.noRekPengirim = nPengirim.noRek
            LEFT JOIN Nasabah nPenerima ON t.noRekPenerima = nPenerima.noRek
            WHERE t.noRekPengirim = ? OR t.noRekPenerima = ?
            """;
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pst1 = conn.prepareStatement(q1);
        pst1.setString(1, noRek);
        pst1.setString(2, noRek);
        ResultSet rs1 = pst1.executeQuery();
        while (rs1.next()) {
            list.add(new RiwayatTransfer(
                rs1.getString("noRekPengirim"),
                rs1.getString("noRekPenerima"),
                rs1.getString("bankPengirim"),
                rs1.getString("bankPenerima"),
                rs1.getDouble("nominal"),
                rs1.getString("namaPengirim"),
                rs1.getString("namaPenerima"),
                rs1.getDate("tanggal").toLocalDate(),
                rs1.getTime("waktu").toLocalTime()
            ));
        }

        // Ambil dari RiwayatTopUp (sebagai pengirim atau penerima)
        String q2 = """
            SELECT DISTINCT t.*, nPengirim.nama AS namaPengirim, nPenerima.nama AS namaPenerima
            FROM RiwayatTopUp t
            LEFT JOIN Nasabah nPengirim ON t.noRekPengirim = nPengirim.noRek
            LEFT JOIN Nasabah nPenerima ON t.noHpPenerima = nPenerima.noHP
            WHERE t.noRekPengirim = ?
               OR t.noHpPenerima = (SELECT noHP FROM Nasabah WHERE noRek = ?)
            """;
        PreparedStatement pst2 = conn.prepareStatement(q2);
        pst2.setString(1, noRek);
        pst2.setString(2, noRek);
        ResultSet rs2 = pst2.executeQuery();
        while (rs2.next()) {
            list.add(new RiwayatTopUp(
                rs2.getString("noRekPengirim"),
                rs2.getString("noHpPenerima"),
                rs2.getString("jenisEWallet"),
                rs2.getDouble("nominal"),
                rs2.getString("namaPengirim"),
                rs2.getString("namaPenerima"),
                rs2.getDate("tanggal").toLocalDate(),
                rs2.getTime("waktu").toLocalTime()
            ));
        }

        // Optional: urutkan berdasarkan tanggal dan waktu jika ingin
        // (tambahkan field tanggal/waktu di Riwayat jika ingin sorting)

        return list;
    }
}
