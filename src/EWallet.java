/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author benja
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class EWallet {

    public static boolean topUp(String NPID, String inputPIN, String inputNominal) {
        try {
            // Bersihkan dan parse input nominal
            double nominal = Double.parseDouble(inputNominal.replace(".", "").replace(",", ""));

            Connection conn = DatabaseConnection.getConnection();

            // Ambil data pengguna
            String query = "SELECT saldo, PIN FROM nonPengguna WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, NPID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String pinDB = rs.getString("PIN");
                double saldo = rs.getDouble("saldo");

                if (inputPIN.equals(pinDB)) {
                    if (nominal <= saldo) {
                        // Kurangi saldo
                        double saldoBaru = saldo - nominal;

                        String updateQuery = "UPDATE nonPengguna SET saldo = ? WHERE id = ?";
                        PreparedStatement pstUpdate = conn.prepareStatement(updateQuery);
                        pstUpdate.setDouble(1, saldoBaru);
                        pstUpdate.setString(2, NPID);
                        pstUpdate.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Top Up Berhasil!");
                        return true;

                    } else {
                        JOptionPane.showMessageDialog(null, "Saldo tidak cukup.");
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "PIN salah.");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data pengguna tidak ditemukan.");
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
        }
        return false;
    }
}
