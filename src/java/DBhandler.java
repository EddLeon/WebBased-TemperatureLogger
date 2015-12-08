/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rperez
 */
public class DBhandler {

    private static Connection connection;

    public DBhandler() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/TempLog", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
             System.out.println("Error Creando DBhandler");
        }
    }
    
    //TODO ADPARLO A TEMP LOGG
    public void loggTemp(double temp, int min, int hora, int mes, int dia, int year) {
        try {
            System.out.println("Logg Temperature");
            Statement statement = connection.createStatement();
            String query = "insert into loggings (minuto, hora, mes, dia, year, temp) values ('" +min+ "','" + hora + "','"+ mes + "','"+ dia + "','"+ year + "','"+ temp + "')";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBhandler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en Store Messages");
        }

    }
    
    public String getTemp(int m, int d){
        String result = "";
        //System.out.println("mes: "+m);
        //System.out.println("dia: "+d);
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM loggings WHERE mes = " + m + " AND dia=" + d;
            ResultSet rs = statement.executeQuery(query);
            result += "<table class=\"highchart\" data-graph-container-before=\"1\" data-graph-type=\"column\"><tr><th>°C</th> <th>hora</th><th>minuto</th></tr>";
            while(rs.next()) {
                result +=  "<tr>";
                double temperatura = rs.getDouble("temp");
                double minuto = rs.getDouble("minuto");
                double hora = rs.getDouble("hora");
                //System.out.println("temp: "+temperatura);
                result +=  "<td>"+(int)temperatura + "</td> ";
                result +=  "<td>  "+(int)hora + "</td> ";
                result +=  "<td>"+(int)minuto + "</td> ";
                result +=  "</tr>";
            }
            result = result.substring(0, result.length()-2 );
            //result += "]";
             result += "</table>";
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBhandler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en Store Messages");
        }
        return result;
    }
    /*
    public static boolean verifyUser(String user, String password){
        try {
            System.out.println("verify user");
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT user, password, password FROM users where user='"+user+"'");
            if(results.next()){
                //System.out.println("Usuario existente y pass valida");
                String pass=results.getString(2);
                if(pass.equals(password))
                    return true;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBhandler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en confirmar User y pass");
        }
        return false;
    }*/

    /*
    public static ArrayList getMessages(String para) {
        ArrayList list = new ArrayList();
        try {            
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT de, contenido, contenido FROM mensajes where para='"+para+"'");
            while (results.next()) {
                String de=results.getString(1);
                String contenido=results.getString(2);
                Mensaje mensaje = new Mensaje(de, para, contenido);
                list.add(mensaje);
            }
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBhandler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en Get Messages");
        }
        return list;
    }
    */
   

}
