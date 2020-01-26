package Com.DLL;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseConnection {

    public Connection getConnection() {

        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "user12";
        String password = "34klq*";

        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String>> executeQuery(String query) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            /////////////metadata//////
            ResultSetMetaData rsmd = rs.getMetaData();
            ArrayList<String> columnNames = new ArrayList<>();
            int columnCount = rsmd.getColumnCount();//be careful for index in column name from metadata it may start from 1
            for (int i = 0; i < columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
            result.add(columnNames);
            ////////////end//////////////

            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                for (int i = 0; i < columnCount; i++) {
                    row.add(rs.getString(i + 1));
                }
                result.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}