package Java.Controllers;

import Java.DAO.DivisionDAO;
import Java.Main.Main;
import Java.Models.Country;
import Java.Models.Customer;
import Java.Models.FirstLevelDivision;
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
 * Controller for Adding Customers.
 * Add customers into the database.
 * extends Customer Validation Controller.
 */

public class AddCustomer extends CustValidationController {
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
     * Combo box using First Level Division model.
     */
    @FXML
    protected ComboBox<FirstLevelDivision> divisionComboBox;
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
     * Combo box using Country model.
     */
    @FXML
    protected ComboBox<Country> countryComboBox;
    /**
     * Text Field for Customer Postal Codes.
     */
    @FXML
    protected TextField custZipTextField;
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
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/Java/Views/Customers.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    /**
     * Saves user input and returns the user to the customer screen.
     * @param event Saves add customer using on Action.
     * @throws IOException signals exception for input and output occurrences
     * @throws SQLException signals exception for SQL occurrences
     */
    @FXML
    void customerSaveButton(ActionEvent event) throws SQLException, IOException {
        String customerName = custNameTextField.getText();
        String address = custAddrTextField.getText();
        String postalCode = custZipTextField.getText();
        String phone = custPhoneTextField.getText();
        String createdBy = Main.getUser();
        if (!validateFields(customerName, address, postalCode, phone)) {
            return;
        }
        int divisionID = divisionComboBox.getValue().getDivisionID();
        Customer customer = new Customer(1111, customerName,address, postalCode, phone, LocalDateTime.now(), createdBy, LocalDateTime.now(), createdBy, divisionID);
        customerDAO.addCustomer(customer);
        sceneChange(event, "/Java/Views/Customers.fxml");
    }

    public void initialize() throws SQLException {
        countryComboBoxSetup();
        divisionComboBoxPrompt();
    }
}
