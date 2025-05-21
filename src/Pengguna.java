import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pengguna {
    private String pid;
    private String npid;
    private String namaLengkap;
    private String noRek;
    private double saldo;
    private String pin;
    private String email;
    private String noHP;
    private String username;

    // Constructor kosong
    public Pengguna() {
    }

    // Method login: mengembalikan objek Pengguna atau null
    public static Pengguna login(String username, String password) throws SQLException {
        String query = "SELECT * FROM Pengguna p JOIN nonPengguna np ON p.NPID = np.id WHERE p.username=? AND p.password=?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return null; // Login gagal
        }

        Pengguna pengguna = new Pengguna();
        pengguna.namaLengkap = rs.getString("nama");
        pengguna.noRek = rs.getString("noRek");
        pengguna.saldo = rs.getDouble("saldo");
        pengguna.pid = rs.getString("id");
        pengguna.npid = rs.getString("NPID");
        pengguna.pin = rs.getString("PIN");
        return pengguna;
    }

    // Getter yang diperlukan
    public String getNamaPertama() {
        return namaLengkap.split(" ")[0];
    }

    public static String verifikasiDataAwal(String nama, String nik, String noRek, String pin, String noHP,
            String email) throws SQLException {
        String query = "SELECT * FROM nonPengguna WHERE nama=? AND NIK=? AND noRek=? AND PIN=? AND noHP=? AND email=?";
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
            return rs.getString("id"); // return NPID
        } else {
            return null;
        }
    }

    public static boolean buatAkun(String username, String password, String npid) throws SQLException {
        String query = "INSERT INTO Pengguna (username, password, NPID) SELECT ?, ?, id FROM nonPengguna WHERE id=?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);
        pst.setString(3, npid);

        int rowsInserted = pst.executeUpdate();
        return rowsInserted > 0;
    }

    public static Pengguna getInfoRekening(String pid) throws SQLException {
        String query = "SELECT * FROM Pengguna p JOIN nonPengguna np ON p.NPID = np.id WHERE p.id = ?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, pid);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            Pengguna pengguna = new Pengguna();
            pengguna.pid = rs.getString("id");
            pengguna.npid = rs.getString("NPID");
            pengguna.namaLengkap = rs.getString("nama");
            pengguna.noRek = rs.getString("noRek");
            pengguna.pin = rs.getString("PIN");
            pengguna.saldo = rs.getDouble("saldo");
            pengguna.email = rs.getString("email");
            pengguna.noHP = rs.getString("noHP");
            pengguna.username = rs.getString("username");
            return pengguna;
        } else {
            return null;
        }
    }

    public String getNoRek() {
        return noRek;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getPID() {
        return pid;
    }

    public String getNPID() {
        return npid;
    }

    public String getPIN() {
        return pin;
    }

    public String getEmail() {
        return email;
    }

    public String getNoHP() {
        return noHP;
    }

    public String getUsername() {
        return username;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }
}
