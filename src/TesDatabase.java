import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesDatabase {
    public static void main(String[] args) {
        try {
            // Ini sekarang opsional sejak JDBC 4.0, tapi bisa dicoba untuk memaksa load
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://192.168.1.100:1433;databaseName=ProyekPemlan;encrypt=false;";
            String user = "pemlan_team2";
            String pass = "acumalaka";

            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi sukses!");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
    }
}