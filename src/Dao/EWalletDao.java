/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author benja
 */
import Database.DatabaseConnection;
import Entity.EWallet;
import Entity.RiwayatTopUp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class EWalletDao {

    // Method untuk validasi nomor HP dan saldo
    public static boolean validasiTopUp(EWallet wallet) throws SQLException {
        String queryNoHP = "SELECT nama FROM Nasabah WHERE noHP = ?";
        String querySaldo = "SELECT saldo, PIN FROM Nasabah WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();

        // Cek nomor HP tujuan
        PreparedStatement pstNoHP = conn.prepareStatement(queryNoHP);
        pstNoHP.setString(1, wallet.getNoHpTujuan());
        ResultSet rsNoHP = pstNoHP.executeQuery();
        if (!rsNoHP.next()) {
            JOptionPane.showMessageDialog(null, "Nomor HP tujuan tidak ditemukan", "Informasi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        wallet.setNamaPenerima(rsNoHP.getString("nama"));

        // Cek saldo dan PIN pengirim
        PreparedStatement pstSaldo = conn.prepareStatement(querySaldo);
        pstSaldo.setString(1, wallet.getPengirim().getNBID());
        ResultSet rsSaldo = pstSaldo.executeQuery();
        if (!rsSaldo.next()) {
            JOptionPane.showMessageDialog(null, "Pengguna tidak ditemukan, silahkan login kembali", "Informasi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        double saldoPengirim = wallet.getPengirim().getSaldo();
        if (saldoPengirim < wallet.getNominal()) {
            JOptionPane.showMessageDialog(null, "Saldo tidak mencukupi", "Informasi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true; // Semua valid
    }

    public static boolean prosesTopUp(EWallet wallet) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        try {
            conn.setAutoCommit(false);

            String updateQuery = "UPDATE Nasabah SET saldo = saldo - ? WHERE id = ?";
            PreparedStatement pstUpdate = conn.prepareStatement(updateQuery);
            pstUpdate.setDouble(1, wallet.getNominal());
            pstUpdate.setString(2, wallet.getPengirim().getNBID());
            int updated = pstUpdate.executeUpdate();


            if (updated > 0) {
                simpanKeRiwayat(conn, wallet);
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    }

    // Simpan riwayat top up
    private static void simpanKeRiwayat(Connection conn, EWallet wallet) throws SQLException {
        RiwayatTopUp riwayat = new RiwayatTopUp(
            wallet.getPengirim().getNoRek(),
            wallet.getNoHpTujuan(),
            wallet.getNamaWallet(),
            wallet.getNominal(),
            wallet.getPengirim().getNamaLengkap(),
            wallet.getNamaPenerima()
        );
        RiwayatDao.simpanRiwayatTopUp(conn, riwayat, wallet.getNoHpTujuan(), wallet.getNamaWallet());
    }
}