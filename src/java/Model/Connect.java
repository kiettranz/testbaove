package Model;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    Connection conn;
    //netstat -an -P TCP
    public Connection getSQLServerConnection()
            throws SQLException, ClassNotFoundException {
        String hostName = "java5.database.windows.net";
        String sqlInstanceName = "";
        String database = "kiettaps04197";
        String userName = "kiettaps04197";
        String password = "1234@qưer";

        return getSQLServerConnection(hostName, sqlInstanceName, database, userName, password);
    }

    public Connection getSQLServerConnection(String hostName,
            String sqlInstanceName, String database, String userName,
            String password) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String connectionURL ="jdbc:sqlserver://java5.database.windows.net:1433;"
                    
                    + "database=kiettaps04197;"
                    + "user=kiettaps04197@java5;"
                    + "password=1234@qưer";
                   
            conn = DriverManager.getConnection(connectionURL);
//            System.out.println("thanh cong");
        } catch (Exception e) {
            System.out.println("that bai "+ e);
        }
        return conn;
    }

}
