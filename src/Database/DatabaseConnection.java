package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        if (connection == null || connection.isClosed()) {
            String url =  "jdbc:sqlserver://localhost\\SQLSERVER:1433;"
                    +"databaseName=ProjectPemlan;"
                    +"user=user_pemlan;"
                    +"password=MBankKuDeveloper_123;"
                    +"encrypt=true;"
                    +"trustServerCertificate=true;";
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
