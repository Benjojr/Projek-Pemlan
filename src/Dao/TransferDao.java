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
import Entity.RiwayatTransfer;
import Entity.Transfer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;

public class TransferDao {

    public static boolean validasiTransfer(Transfer transfer) throws SQLException {
        String queryTujuan = "SELECT nama FROM Nasabah WHERE noRek=? AND Bank=?";
        String querySaldo = "SELECT saldo FROM Nasabah WHERE id=?";
        Connection conn = DatabaseConnection.getConnection();

        // Cek rekening tujuan
        PreparedStatement pstTujuan = conn.prepareStatement(queryTujuan);
        pstTujuan.setString(1, transfer.getNoRekPenerima());
        pstTujuan.setString(2, transfer.getBankPenerima());
        ResultSet rsTujuan = pstTujuan.executeQuery();
        if (!rsTujuan.next()) {
            JOptionPane.showMessageDialog(null, "Nomor rekening tidak ditemukan", "Informasi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        transfer.setNamaPenerima(rsTujuan.getString("nama"));

        // Cek saldo pengirim
        PreparedStatement pstSaldo = conn.prepareStatement(querySaldo);
        pstSaldo.setString(1, transfer.getPengirim().getNBID());
        ResultSet rsSaldo = pstSaldo.executeQuery();
        if (!rsSaldo.next()) {
            JOptionPane.showMessageDialog(null, "Pengguna tidak ditemukan, silahkan login kembali", "Informasi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        double saldoPengirim = rsSaldo.getDouble("saldo");
        if (saldoPengirim < transfer.getNominal()) {
            JOptionPane.showMessageDialog(null, "Saldo tidak mencukupi", "Informasi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true; // Semua valid
    }

    public static boolean prosesTransfer(Transfer transfer, String pengirimNBID, String pinInput, String PIN)
            throws SQLException {
        if (!pinInput.equals(PIN)) {
            return false;
        }

        Connection conn = DatabaseConnection.getConnection();
        try {
            conn.setAutoCommit(false);

            String querySaldo = "SELECT saldo FROM Nasabah WHERE id = ?";
            PreparedStatement cekSaldoStmt = conn.prepareStatement(querySaldo);
            cekSaldoStmt.setString(1, pengirimNBID);
            ResultSet rs = cekSaldoStmt.executeQuery();
            if (!rs.next()) {
                conn.rollback();
                JOptionPane.showMessageDialog(null, "Pengguna tidak ditemukan.");
                return false;
            }

            String queryTambah = "UPDATE Nasabah SET saldo = saldo + ? WHERE noRek = ?";
            PreparedStatement pst1 = conn.prepareStatement(queryTambah);
            pst1.setDouble(1, transfer.getNominal());
            pst1.setString(2, transfer.getNoRekPenerima());
            int updated1 = pst1.executeUpdate();

            String queryKurang = "UPDATE Nasabah SET saldo = saldo - ? WHERE id = ?";
            PreparedStatement pst2 = conn.prepareStatement(queryKurang);
            pst2.setDouble(1, transfer.getNominal());
            pst2.setString(2, pengirimNBID);
            int updated2 = pst2.executeUpdate();

            if (updated1 > 0 && updated2 > 0) {
                simpanKeRiwayat(conn, transfer);
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

    

     private static void simpanKeRiwayat(Connection conn, Transfer transfer) throws SQLException {
        RiwayatTransfer riwayat = new RiwayatTransfer(
            transfer.getPengirim().getNoRek(),
            transfer.getNoRekPenerima(),
            transfer.getPengirim().getBank(),
            transfer.getBankPenerima(),
            transfer.getNominal(),
            transfer.getPengirim().getNamaLengkap(),
            transfer.getNamaPenerima(),
            LocalDate.now(),
            LocalTime.now()
        );
        RiwayatDao.simpanRiwayatTransfer(conn, riwayat);
    }
}
