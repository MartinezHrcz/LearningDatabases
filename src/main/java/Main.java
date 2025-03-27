import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        System.out.println("Password for db: ");
        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();

        try {
            Class.forName("org.postgresql.Driver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

        Driver driver = new org.postgresql.Driver();

        try{
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.out.println("Error: unable to register driver!");
            System.exit(1);
        }

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(jdbcUrl,username,password);
        } catch (SQLException e) {
            System.out.println("Error: unable to connect to database!");
            System.exit(1);
        }

        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Error: unable to execute statement!");
            System.exit(1);
        }

        ResultSet rs = null;

        try {
            rs = stmt.executeQuery("Select * from cars;");
        } catch (SQLException e) {
            System.out.println("Error: unable to execute statement!");
            System.exit(1);
        }

        System.out.println("Database Contents: ");

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
}
