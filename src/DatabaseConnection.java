import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:sqlserver://benxyz.database.windows.net:1433;"
                    + "database=ProjectPemlan;"
                    + "user=hihanghoheng@benxyz;"
                    + "password=Mucacos_26;"
                    + "encrypt=true;"
                    + "trustServerCertificate=false;"
                    + "hostNameInCertificate=*.database.windows.net;"
                    + "loginTimeout=30;";
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(url);
            } catch (ClassNotFoundException e) {
                System.out.println("SQL Server driver tidak ditemukan!");
            }
        }
        return connection;
    }
}
