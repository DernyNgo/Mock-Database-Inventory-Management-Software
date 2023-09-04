package Java.Controllers;

import Java.Main.Main;
import Java.Models.Country;
import Java.Models.Customer;
import Java.Models.FirstLevelDivision;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Daniel Ngo
 * Update Customer Controller Screen
 * Extends Customer Validation Controller
 */

public class UpdateCustomer extends CustValidationController {
    /**
     * References stage and parent scenes.
     */
    Stage stage;
    Parent scene;
    /**
     * Text Field for Customer Addresses.
     */
    @FXML
    protected TextField custAddrTextField;
    /**
     * Combo box using Country model.
     */
    @FXML
    protected ComboBox<Country> countryComboBox;
    /**
     * Text Field for Customer ID.
     */
    @FXML
    protected TextField custIDTextField;
    /**
     * Text Field for Customer Names.
     */
    @FXML
    protected TextField custNameTextField;
    /**
     * Text Field for Customer Phone Numbers.
     */
    @FXML
    protected TextField custPhoneTextField;
    /**
     * Combo box using Division model.
     */
    @FXML
    protected ComboBox<FirstLevelDivision> divisionComboBox;
    /**
     * Text Field for Customer Postal Codes.
     */
    @FXML
    protected TextField custZipTextField;
    /**
     * Customer instantiation.
     */
    private Customer customer;
    /**
     * Cancels user input and prompts the user to return to the customer screen.
     * @param event Cancels add customer using on Action
     * @throws IOException signals exception for input and output occurrences
     */
    @FXML
    void customerCancelButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation:");
        alert.setContentText("Return to the customer view? Changes will not be saved.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            sceneChange(event, "/Java/Views/Customers.fxml");
        }
    }
    /**
     * Saves user input and returns the user to the customer screen.
     * @param event Saves add customer using on Action.
     * @throws IOException signals exception for input and output occurrences
     * @throws SQLException signals exception for SQL occurrences
     */
    @FXML
    void customerSaveButton(ActionEvent event) throws IOException, SQLException {
        String customerName = custNameTextField.getText();
        String address = custAddrTextField.getText();
        String postalCode = custZipTextField.getText();
        String phone = custPhoneTextField.getText();
        String updatedBy = Main.getUser();
        if (!validateFields(customerName, address, postalCode, phone)) {
            return;
        }
        int divisionID = divisionComboBox.getValue().getDivisionID();
        Customer updatedCustomer = new Customer(customer.getCustomerID(), customerName, address, postalCode, phone,
                customer.getCreateDate(), customer.getCreatedBy(), LocalDateTime.now(), updatedBy, divisionID);
        customerDAO.updateCustomer(updatedCustomer);
        sceneChange(event, "/Java/Views/Customers.fxml");
    }

    /**
     * Sets the updated Customer.
     * @param customer Customer to be updated
     * @throws SQLException Signals Exception for SQL occurrences
     */
    public void setCustomer(Customer customer) throws SQLException {
        this.customer = customer;
        custIDTextField.setText(Integer.toString(customer.getCustomerID()));
        custNameTextField.setText(customer.getCustomerName());
        custAddrTextField.setText(customer.getAddress());
        custZipTextField.setText(customer.getPostalCode());
        custPhoneTextField.setText(customer.getPhone());
        countryComboBoxSetup();

        FirstLevelDivision division = divisionDAO.getFirstLevelDivision(customer.getDivisionID());
        Country country = countryDAO.getCountry(division.getCountryID());
        ObservableList<FirstLevelDivision> divisions = divisionDAO.getDivisionsByCountryID(country.getCountryID());
        countryComboBox.setValue(country);
        divisionComboBox.setItems(divisions);
        divisionComboBox.setValue(division);
    }
    /**
     * Initializes Update Customer
     * Sets combo box for Country
     * Sets combo box for Division
     * @throws SQLException Signals Exception for SQL occurrences
     */
    public void initialize() throws SQLException {
        countryComboBoxSetup();
        divisionComboBoxPrompt();
    }
}
