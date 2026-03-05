public class DBUTILS {import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    public class DBUtils {
        public static Connection establishConnection() {
            Connection con = null;

            try {
                String url = "jdbc:mysql://localhost:3305/lab07";
                String user = "root";
                String password = "Rizki#123";

                con = DriverManager.getConnection(url, user, password);
            return con;
        }
    }
}
