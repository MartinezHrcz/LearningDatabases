package Database;

import java.sql.*;
import java.util.Scanner;

public class Database {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static String password = "";

    private static Connection conn = null;
    private static Driver driver = new org.postgresql.Driver();

    public static void StartConnection() {
        DriverConnection();

        DriverRegister();

        UserLogin();

        DatabaseConnect();

        DatabaseQueryUtil.WriteToDB("Ford", "Transporter",2019, conn);

        ShowAllData();

        Disconnect();
    }

    //Checks if the driver class exists
    private static void DriverConnection() {

        try {
            Class.forName("org.postgresql.Driver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

    }

    //Registers the driver
    private static void DriverRegister() {

        try{
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.out.println("Error: unable to register driver!");
            System.exit(1);
        }
    }

    //Asks for the user password
    private static void UserLogin(){
        System.out.println("Password for db: ");
        Scanner sc = new Scanner(System.in);
        password = sc.nextLine();
    }

    //Makes connection
    private static void DatabaseConnect() {

        try {
            conn = DriverManager.getConnection(JDBC_URL,USERNAME,password);
        } catch (SQLException e) {
            System.out.println("Error: unable to connect to database!");
            System.exit(1);
        }

    }

    //Prints everything to the console
    private static void ShowAllData() {
        /*
        try{
            DatabaseQueryUtil.ShowAll(conn);
        } catch (SQLException e) {
            System.out.println("Error: unable to show database!");
        }

         */


        Statement stmt = null;

        //Creates statement
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Error: unable to execute statement!");
            System.exit(1);
        }

        ResultSet rs = null;

        //Exocutes the query
        try {
            rs = stmt.executeQuery("Select * from cars;");
        } catch (SQLException e) {
            System.out.println("Error: unable to execute statement!");
            System.exit(1);
        }

        System.out.println("Database data:");

        try {
            while (rs.next()) {

                String brand = rs.getString("brand");
                String model = rs.getString("model");
                int year = rs.getInt("year");
                System.out.println(brand + " " + model + " " + year);

            }

        } catch (SQLException e) {
            System.out.println("Error: unable to execute statement!");
            System.exit(1);
        }
    }

    //Disconnects from the database
    private static void Disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
