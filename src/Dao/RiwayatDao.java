/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author benja
 */
import Entity.Riwayat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class RiwayatDao {

    public static void simpanRiwayatTransfer(Connection conn, Riwayat riwayat) throws SQLException {
        String query = """
                INSERT INTO RiwayatTransfer(noRekPengirim, noRekPenerima, bankPengirim, bankPenerima, tanggal, waktu)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, riwayat.getNoRekPengirim());
        pst.setString(2, riwayat.getNoRekPenerima());
        pst.setString(3, riwayat.getBankPengirim());
        pst.setString(4, riwayat.getBankPenerima());
        pst.setObject(5, LocalDate.now());
        pst.setObject(6, LocalTime.now());
        pst.executeUpdate();
    }

    public static void simpanRiwayatTopUp(Connection conn, Riwayat riwayat, String noHpPenerima, String jenisEWallet) throws SQLException {
        String query = """
                INSERT INTO RiwayatTopUp(noRekPengirim, noHpPenerima, jenisEWallet, tanggal, waktu)
                VALUES (?, ?, ?, ?, ?)
                """;
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, riwayat.getNoRekPengirim());
        pst.setString(2, noHpPenerima);
        pst.setString(3, jenisEWallet);
        pst.setObject(4, LocalDate.now());
        pst.setObject(5, LocalTime.now());
        pst.executeUpdate();
    }
}
