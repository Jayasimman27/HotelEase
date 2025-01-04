package Hotel.Managment.System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class con {
    //public Connection c;
    Connection connection;
    Statement statement;

    public con(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelMS", "root", "Simman@27");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}