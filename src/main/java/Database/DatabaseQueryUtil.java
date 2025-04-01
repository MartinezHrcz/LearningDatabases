package Database;

import java.sql.*;

public class DatabaseQueryUtil {
    public static void ShowAll(Connection conn) throws SQLException {

        Statement stmt = null;

        stmt = conn.createStatement();
        ResultSet rs = null;

        rs = stmt.executeQuery("select * from Database");
        DatabaseMetaData dmd = conn.getMetaData();
        int length= 0;
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            System.out.print(rs.getMetaData().getColumnName(i) +" | ");
            length += rs.getMetaData().getColumnName(i).length();
            length += 3;
        }
        for (int i = 1; i <= length; i++) {
            System.out.print("-");
        }


        while (rs.next()) {
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                System.out.print(rs.getMetaData().getColumnName(i) + "\t");
            }
        }
    }

    public static void WriteToDB(String brand, String model, int year,Connection conn) {

        ResultSet rs = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cars(brand,model,year) VALUES (?,?,?)");
            stmt.setString(1, brand);
            stmt.setString(2, model);
            stmt.setInt(3, year);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Can't update the database!");
        }
    }
}
