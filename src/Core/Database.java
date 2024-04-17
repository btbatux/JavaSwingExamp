package Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection connection()
    {
        Connection connection=null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoDb",
                    "postgres","123123");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
