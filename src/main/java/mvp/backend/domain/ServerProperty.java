package mvp.backend.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServerProperty {
    //public static String CONNECTION_STRING = "jdbc:sqlserver://WIN-7EOGHJ02TEH;database=PromoPlannerMVP;user=WEB_User;password=HelloWorld123%;loginTimeout=30;";
    public static String CONNECTION_STRING = "jdbc:sqlserver://95.165.11.179:1433;database=PromoPlannerMVP;user=WEB_User;password=HelloWorld123%;loginTimeout=30;";
    //public static String CONNECTION_STRING = "jdbc:sqlserver://WIN-7EOGHJ02TEH;database=WEDGE2;user=WEB_User;password=sPEP12bk0;loginTimeout=30;";
    //public static String CONNECTION_STRING = "jdbc:sqlserver://95.165.11.179:1433;database=WEDGE2;user=WEB_User;password=sPEP12bk0;loginTimeout=30;";
    //public static String CONNECTION_STRING_ADDRESSES = "jdbc:sqlserver://95.165.11.179:1433;database=LIGHT;user=WEB_User;password=sPEP12bk0;loginTimeout=30;";
    //public static String TEMP_PATH = "C:\\Users\\s.petranin\\Downloads\\temp\\";
    //public static String TEMP_PATH = "C:\\Users\\Сергей\\Downloads\\temp\\";

    public static Connection CreateTransactionConnection() {
        try{
            Connection connection = DriverManager.getConnection(ServerProperty.CONNECTION_STRING);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            return connection;
        } catch (SQLException e){
            return null;
        }
    }
}
