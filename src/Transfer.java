import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Transfer {
    private String noRekTujuan;
    private String bankTujuan;
    private double nominal;

    public Transfer(String noRekTujuan, String bankTujuan, double nominal) {
        this.noRekTujuan = noRekTujuan;
        this.bankTujuan = bankTujuan;
        this.nominal = nominal;
    }

    public boolean rekeningTujuanValid() throws SQLException {
        String query = "SELECT * FROM nonPengguna WHERE noRek=? AND Bank=?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, noRekTujuan);
        pst.setString(2, bankTujuan);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }

    public boolean prosesTransfer(String pengirimNPID, String pinInput, String storedPIN) throws SQLException {
        if (!pinInput.equals(storedPIN))
            return false;

        Connection conn = DatabaseConnection.getConnection();
        try {
            conn.setAutoCommit(false);

            String querySaldo = "SELECT saldo FROM nonPengguna WHERE id = ?";
            PreparedStatement cekSaldoStmt = conn.prepareStatement(querySaldo);
            cekSaldoStmt.setString(1, pengirimNPID);
            ResultSet rs = cekSaldoStmt.executeQuery();
            if (!rs.next()) {
                conn.rollback();
                JOptionPane.showMessageDialog(null, "Pengguna tidak ditemukan.");
                return false;
            }
            double saldoPengirim = rs.getDouble("saldo");
            if (saldoPengirim < nominal) {
                conn.rollback();
                JOptionPane.showMessageDialog(null, "Saldo tidak mencukupi.");
                return false;
            }
            // Tambah saldo penerima
            String queryTambah = "UPDATE nonPengguna SET saldo = saldo+? WHERE noRek=?";
            PreparedStatement pst1 = conn.prepareStatement(queryTambah);
            pst1.setDouble(1, nominal);
            pst1.setString(2, noRekTujuan);
            int updated1 = pst1.executeUpdate();

            // Kurangi saldo pengirim
            String queryKurang = "UPDATE nonPengguna SET saldo = saldo-? WHERE id =?";
            PreparedStatement pst2 = conn.prepareStatement(queryKurang);
            pst2.setDouble(1, nominal);
            pst2.setString(2, pengirimNPID);
            int updated2 = pst2.executeUpdate();

            if (updated1 > 0 && updated2 > 0) {
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

    public static double getSaldoBaru(String npid) throws SQLException {
        String query = "SELECT saldo FROM nonPengguna WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, npid);
        ResultSet rs = pst.executeQuery();
        if (rs.next())
            return rs.getDouble("saldo");
        else
            return -1;
    }

    public double getNominal() {
        return nominal;
    }
}
