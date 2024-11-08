
package studentmanagmentsystem.db;

import  java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "3zz@t011430030";
    private static final String DATACONN = "jdbc:mysql://localhost:3306/student_management ";
    private static Connection con = null;
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con =   DriverManager.getConnection(DATACONN, USERNAME, PASSWORD);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }
}
