package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class configDB {
    public static Connection  conexion = null;
    public static Connection openConexion(){
        var url = "jdbc:mysql://localhost:3306/RiwiAcademyDB";
        var user ="root";
        var password ="";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url,user,password);
            System.out.println("conexion ala database");


        }catch (ClassNotFoundException e){
            System.out.println("error driver " +e.getMessage() );

        }
        catch(SQLException e){
            System.out.println("ERROR de conexion:" + e.getMessage());
        }
        return conexion;
    }

}
