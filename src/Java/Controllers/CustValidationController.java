package Java.Controllers;

import Java.DAO.CountryDAO;
import Java.DAO.CustomerDAO;
import Java.DAO.DivisionDAO;
import Java.Models.Country;
import Java.Models.FirstLevelDivision;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * @author Daniel Ngo
 * Controller for Validating Data for other Customer controllers.
 * Extends the Method Controller.
 */

public abstract class CustValidationController extends MethodController {
    /**
     * References Customer DAO.
     */
    public CustomerDAO customerDAO = new CustomerDAO();
    /**
     * References Country DAO.
     */
    public CountryDAO countryDAO = new CountryDAO();
    /**
     * References Division DAO.
     */
    public DivisionDAO divisionDAO = new DivisionDAO();
    /**
     * Text field for Customer Addresses.
     */
    @FXML
    protected TextField custAddrTextField;
    /**
     * Text field for Customer Names.
     */
    @FXML
    protected TextField custNameTextField;
    /**
     * Text field for Customer Phone Numbers.
     */
    @FXML
    protected TextField custPhoneTextField;
    /**
     * Text field for Customer Postal Codes.
     */
    @FXML
    protected TextField custZipTextField;
    /**
     * Combo box using Country model.
     */
    @FXML
    public ComboBox<Country> countryComboBox;
    /**
     * Combo box using First Level Division model.
     */
    @FXML
    public ComboBox<FirstLevelDivision> divisionComboBox;
    /**
     * Label to change based on Country ID.
     */
    @FXML
    private Label countryLabel;

    @FXML
    private Label addressError;

    @FXML
    private Label nameError;

    @FXML
    private Label phoneError;

    @FXML
    private Label zipCodeError;

    /**
     * This method is called when the user chooses a country from the combo box.
     * Sets the division combo box
     * Enables division combo box
     * @param event Dictates country label based on Country ID selection.
     * @throws SQLException signals exception for SQL occurrences
     */
    @FXML
    void countryComboBoxAction(ActionEvent event) throws SQLException {
        int countryID = countryComboBox.getValue().getCountryID();
        divisionComboBox.setItems(divisionDAO.getDivisionsByCountryID(countryID));
        divisionComboBox.setValue(null);
        divisionComboBox.setDisable(false);
        if (countryID == 1) {
            countryLabel.setText("State");
        } else if (countryID == 3) {
            countryLabel.setText("Province");
        } else {
            countryLabel.setText("Division");
        }
    }

    /**
     * Sets country combo box to all countries from the database.
     */
    public void countryComboBoxSetup() throws SQLException {
        countryComboBox.setItems(countryDAO.getAllCountries());
    }

    /**
     * Sets the text for the division combo box.
     */
    public void divisionComboBoxPrompt() {
        divisionComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(FirstLevelDivision item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "Select an option" : item.getDivision());
            }
        });
    }

    /**
     * Validates fields in the Add Customer Screen.
     * @param customerName Customer Name text field validation.
     * @param address Customer Address text field validation.
     * @param postalCode Customer Postal Code text field validation.
     * @param phone Customer Phone NUmber text field validation.
     * @return Returns validation Boolean
     */
    protected boolean validateFields(String customerName, String address, String postalCode, String phone) {
        boolean validationBoolean = true;

        if (customerName.isEmpty()) {
            nameError.setText("Customer name is required.");
            nameError.setStyle("-fx-text-fill: red");
            validationBoolean = false;
        } else {
            nameError.setText("Customer Name");
        }
        if (address.isEmpty()) {
            addressError.setText("Address is required.");
            addressError.setStyle("-fx-text-fill: red");
            validationBoolean = false;
        } else {
            addressError.setText("Address");
        }
        if (postalCode.isEmpty()) {
            zipCodeError.setText("Postal code is required.");
            zipCodeError.setStyle("-fx-text-fill: red");
            validationBoolean = false;
        } else {
            zipCodeError.setText("Postal Code");
        }
        if (phone.isEmpty()) {
            phoneError.setText("Phone number is required.");
            phoneError.setStyle("-fx-text-fill: red");
            validationBoolean = false;
        } else {
            phoneError.setText("Phone Number");
        }
        if (divisionComboBox.getValue() == null) {
            errorHandler("Division Error", "Please select a division.", "Division is required!");
            validationBoolean = false;
        }
        return validationBoolean;
    }
}
