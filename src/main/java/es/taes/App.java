package es.taes;

import java.sql.Statement;
import java.util.Scanner;

import com.mysql.fabric.xmlrpc.base.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {

  // JDBC driver name and database URL
  static final String JDBC_DRIVER = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource";
  static final String DB_URL = "jdbc:mysql://iprocuratio.com:3333/miguel";

  // Database credentials
  static final String USER = "root";
  static final String PASS = "once012020";

  public static void main(final String args[]) throws ClassNotFoundException, SQLException {
    
        Connection conn = null;
        Statement stmt = null;

        // Register JDBC driver
        Class.forName(JDBC_DRIVER);

        // Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        // Execute a query
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT id, first, last, age FROM Employees";
        stmt.executeUpdate(
                "CREATE TABLE if not exists Employees ( id INT(11) PRIMARY KEY, first VARCHAR(256),  last VARCHAR(256),age INTEGER)");
                
                final Scanner scan = new Scanner (System.in);

          System.out.print("Pulsa 1 para crear un nuevo empleado u otra tecla para salir: ");
          final String menu = scan.nextLine();

              System.out.println(menu.length());
              
              if (menu.equals("1")) {

                  System.out.print("Nombre del nuevo empleado: ");
                  final String name = scan.nextLine();
                  System.out.print("Apellido del nuevo empleado: ");
                  final String sName = scan.nextLine();
                  System.out.print("Edad del nuevo empleado: ");
                  final String age = scan.nextLine();
                
                  String insert = "INSERT INTO  Employees (first, last, age) Values ('"+name+"', '"+sName+"', "+age+")";
                  stmt.executeUpdate(insert);

                        System.out.print("Empleado añadido");
              
              } else {
              return;   
              }
          ;
        final ResultSet rs = stmt.executeQuery(sql);

      // Extract data from result set
      while (rs.next()) {
        // Retrieve by column name
        final int id = rs.getInt("id");
        final int age = rs.getInt("age");
        final String first = rs.getString("first");
        final String last = rs.getString("last");

        System.out.print("ID: " + id);
        System.out.print(", Age: " + age);
        System.out.print(", First: " + first);
        System.out.println(", Last: " + last);
      }
      // Clean-up environment
      rs.close();
      stmt.close();
      conn.close();

    }

  private static void Values(String name, String sName, Integer age) {
  }
}
