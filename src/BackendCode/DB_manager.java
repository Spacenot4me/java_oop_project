package BackendCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class DB_manager {
    public static final String URL  = "jdbc:postgresql://localhost:5432/rent_car";
    public static final String USER  = "postgres";
    public static final String PASSWORD  = "admin";
    public static Connection connection;

    public static void connect() {
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
        }catch (Exception e){
            System.out.println("Error in connecting to the PostgreSQL server.");
            e.printStackTrace();
        }
    }
}
