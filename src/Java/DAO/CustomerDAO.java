package Java.DAO;

import Java.Helper.JDBC;
import Java.Models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static Java.Helper.JDBC.connection;

/**
 * @author Daniel Ngo
 * Implements the CustomerDAO Interface.
 */
public class CustomerDAO implements CustomerDAOInterface {

    /**
     * Gets a customer from the database.
     * @param customerID The ID of the customer to get
     * @return Returns the customer with the specific ID
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public Customer getCustomer(int customerID) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");

            return new Customer(customerID, customerName, address, postalCode, phone, createDate.toLocalDateTime(),
                    createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy, divisionID);
        }
        return null;
    }

    /**
     * Adds a customer to the database.
     * @param customer The customer to add
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, " +
                "Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setTimestamp(5, Timestamp.valueOf(customer.getCreateDate()));
        ps.setString(6, customer.getCreatedBy());
        ps.setTimestamp(7, Timestamp.valueOf(customer.getLastUpdate()));
        ps.setString(8, customer.getLastUpdatedBy());
        ps.setInt(9, customer.getDivisionID());
        ps.executeUpdate();
    }

    /**
     * Updates a customer in the database.
     * @param customer The customer to update
     * @throws SQLException Signals Exception for SQL occurrences
     * */
    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, " +
                "Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setTimestamp(5, Timestamp.valueOf(customer.getLastUpdate()));
        ps.setString(6, customer.getLastUpdatedBy());
        ps.setInt(7, customer.getDivisionID());
        ps.setInt(8, customer.getCustomerID());
        ps.executeUpdate();
    }

    /**
     * Deletes a customer from the database.
     * @param customerID The ID of the customer to delete
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public void deleteCustomer(int customerID) throws SQLException {
        new AppointmentDAO().deleteAppointmentsByCustomerID(customerID);
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        ps.executeUpdate();
    }

    /**
     * Gets all customers from the database.
     * @return Returns an ObservableList of all customers
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customers";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");

            customers.add(new Customer(customerID, customerName, address, postalCode, phone, createDate.toLocalDateTime(),
                    createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy, divisionID));
        }
        return customers;
    }
    /**
     * Gets all Postal Codes from the database.
     * @return Returns an ObservableList of all postal codes
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public ObservableList<Customer> getAllPostalCodes(String postalCode) throws SQLException {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customers WHERE Postal_Code = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, postalCode);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String phone = rs.getString("Phone");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");

            customers.add(new Customer(customerID, customerName, address, postalCode, phone, createDate.toLocalDateTime(),
                    createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy, divisionID));
        }
        return customers;
    }
    /**
     * Gets all distinct postal codes from the database.
     * @return Returns an ObservableList of distinct postal codes
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public ObservableList<String> getDistinctPostalCodes() throws SQLException {
        ObservableList<String> postalCodes = FXCollections.observableArrayList();

        String sql = "SELECT DISTINCT Postal_Code FROM customers";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            postalCodes.add(rs.getString("Postal_Code"));
        }
        return postalCodes;
    }
}
