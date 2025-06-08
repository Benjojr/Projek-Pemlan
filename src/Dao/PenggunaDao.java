/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.DatabaseConnection;
import Entity.Pengguna;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author benja
 */
public class PenggunaDao {

    public static Pengguna login(String username, String password) throws SQLException {
        String query = "SELECT * FROM Pengguna p JOIN Nasabah n ON p.NBID = n.id WHERE p.username=? AND p.password=?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();

        if (!rs.next())
            return null;

        return new Pengguna(
                rs.getString("id"),
                rs.getString("NBID"),
                rs.getString("nama"),
                rs.getString("noRek"),
                rs.getDouble("saldo"),
                rs.getString("PIN"),
                rs.getString("email"),
                rs.getString("noHP"),
                rs.getString("username")
        );
    }

    public static String verifikasiDataAwal(String nama, String nik, String noRek, String pin, String noHP,
            String email) throws SQLException {
        String query = """
                SELECT n.id, p.id AS pengguna_id
                FROM Nasabah n
                LEFT OUTER JOIN Pengguna p ON np.id = p.NPID
                WHERE np.nama=? AND np.NIK=? AND np.noRek=? AND np.PIN=? AND np.noHP=? AND np.email=?
                """;

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, nama);
        pst.setString(2, nik);
        pst.setString(3, noRek);
        pst.setString(4, pin);
        pst.setString(5, noHP);
        pst.setString(6, email);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            if (rs.getString("pengguna_id") != null) {
                return "TERDAFTAR";
            } else {
                return rs.getString("id");
            }
        } else {
            return null;
        }
    }

    public static boolean buatAkun(String username, String password, String npid) throws SQLException {
        String query = "INSERT INTO Pengguna (username, password, NBID) SELECT ?, ?, id FROM Nasabah WHERE id=?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);
        pst.setString(3, npid);
        int rowsInserted = pst.executeUpdate();
        return rowsInserted > 0;
    }
    
    public static double getSaldoBaru(Pengguna pengguna) throws SQLException {
        String query = "SELECT saldo FROM Nasabah WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, pengguna.getNBID());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getDouble("saldo");
        } else {
            return -1;
        }
    }

}
