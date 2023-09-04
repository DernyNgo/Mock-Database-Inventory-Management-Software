package Java.DAO;

import Java.Models.Customer;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CustomerDAOInterface {
    public Customer getCustomer(int customerID) throws SQLException;
    public void addCustomer(Customer customer) throws SQLException;
    public void updateCustomer(Customer customer) throws SQLException;
    public void deleteCustomer(int customerID) throws SQLException;
    public ObservableList<Customer> getAllCustomers() throws SQLException;
    public ObservableList<Customer> getAllPostalCodes(String postalCode) throws SQLException;
    public ObservableList<String> getDistinctPostalCodes() throws SQLException;
}
