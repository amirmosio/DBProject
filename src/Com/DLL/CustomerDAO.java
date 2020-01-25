package Com.DLL;

import Com.Model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    DataBaseConnection dbc = new DataBaseConnection();

    public Customer select(String Id) {
        Customer customer = null;
        String query = "SELECT NAME FROM CUSTOMER WHERE CUSTOMER.ID=?";
        try {
            Connection conn = dbc.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, Id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setNAME(rs.getString("NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

}
