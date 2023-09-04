package Java.Controllers;

import Java.DAO.*;
import Java.Models.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Daniel Ngo
 * Reports Country View Controller.
 * Extends Reports Controller.
 */

public class ReportsPostalCode extends Reports {
    /**
     * References stage and parent scenes.
     */
    Stage stage;
    Parent scene;
    /**
     * Table Column for Appointment Customer IDs.
     */
    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomerIDColumn;
    /**
     * Table Column for Appointment Descriptions.
     */
    @FXML
    private TableColumn<Appointment, String> appointmentDescColumn;
    /**
     * Table Column for Appointment End Date and Times.
     */
    @FXML
    private TableColumn<Appointment, LocalDateTime> appointmentEndColumn;
    /**
     * Table Column for Appointment IDs.
     */
    @FXML
    private TableColumn<Appointment, Integer> appointmentIDColumn;
    /**
     * Table Column for Appointment Locations.
     */
    @FXML
    private TableColumn<Appointment, String> appointmentLocationColumn;
    /**
     * Table Column for Appointment Start Date and Times.
     */
    @FXML
    private TableColumn<Appointment, LocalDateTime> appointmentStartColumn;
    /**
     * Table Column for Appointment Titles.
     */
    @FXML
    private TableColumn<Appointment, String> appointmentTitleColumn;
    /**
     * Table Column for Appointment Types.
     */
    @FXML
    private TableColumn<Appointment, String> appointmentTypeColumn;
    /**
     * Label for Reports Total Counter.
     */
    @FXML
    protected Label reportsTotalCounter;
    /**
     * Combo box using Contact model.
     */
    @FXML
    protected ComboBox<String> reportsComboBox;
    /**
     * Table View for reports using Appointment model.
     */
    @FXML
    private TableView<Customer> reportsTableView;
    /**
     * References Contact DAO.
     */
    private ContactDAO contactDAO = new ContactDAO();
    /**
     * References Appointment DAO.
     */
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    /**
     * References Country DAO.
     */
    private CountryDAO countryDAO = new CountryDAO();
    /**
     * References Division DAO.
     */
    private DivisionDAO divisionDAO = new DivisionDAO();

    private CustomerDAO customerDAO = new CustomerDAO();
    /**
     * Table Column for Customer Addresses.
     */
    @FXML
    protected TableColumn<Customer, String> customerAddressTextField;
    /**
     * Table Column for Customer IDs.
     */
    @FXML
    protected TableColumn<Customer, Integer> customerIDTextField;
    /**
     * Table Column for Customer Names.
     */
    @FXML
    protected TableColumn<Customer, String> customerNameTextField;
    /**
     * Table Column for Customer Phone Numbers.
     */
    @FXML
    protected TableColumn<Customer, String> customerPhoneTextField;
    /**
     * Table Column for Customer States or Provinces.
     */
    @FXML
    protected TableColumn<Customer, String> customerStateProvinceText;
    /**
     * Table Column for Customer Postal Codes.
     */
    @FXML
    protected TableColumn<Customer, String> customerZipTextField;
    /**
     * Customer Table View.
     */

    /**
     * Returns user to the appointments screen.
     * Prompts user confirmation to return to the appointments screen.
     * @param event Switches screens using On Action
     * @throws IOException Signals exception for input and output occurrences
     */
    @FXML
    void appointmentViewAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation:");
        alert.setContentText("Return to the appointment scheduler?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            sceneChange(event, "/Java/Views/MainScreen.fxml");
        }
    }
    /**
     * Switches user to the report's contacts screen.
     * @param event Switches screens using On Action
     * @throws IOException Signals exception for input and output occurrences
     */
    @FXML
    void contactButtonAction(ActionEvent event) throws IOException {
        sceneChange(event, "/Java/Views/Reports.fxml");
    }
    /**
     * Switches user to the report's months screen.
     * @param event Switches screens using On Action
     * @throws IOException Signals exception for input and output occurrences
     */
    @FXML
    void customerMonthButtonAction(ActionEvent event) throws IOException {
        sceneChange(event, "/Java/Views/ReportsMonth.fxml");
    }
    /**
     * Switches user to the report's country screen.
     * @param event Switches screens using On Action
     * @throws IOException Signals exception for input and output occurrences
     */
    @FXML
    void customerPostalCodeButton(ActionEvent event) throws IOException {
        sceneChange(event, "/Java/Views/ReportsPostalCode.fxml");
    }
    /**
     * Switches user to the report's type screen.
     * @param event Switches screens using On Action
     * @throws IOException Signals exception for input and output occurrences
     */
    @FXML
    void customerTypeButtonAction (ActionEvent event) throws IOException {
        sceneChange(event, "/Java/Views/ReportsType.fxml");
    }
    /**
     * Sets Combo box using data from DAO.
     * @param event Sets combo boxes using On Action
     * @throws SQLException Signals exception for SQL occurrences
     */
    @FXML
    void reportsComboBoxAction(ActionEvent event) throws SQLException {
        ObservableList<Customer> customers = customerDAO.getAllPostalCodes(reportsComboBox.getValue());
        reportsTableView.setItems(customers);
        reportsTotalCounter.setText("Postal Code Appointments: " + customers.size());
    }
    /**
     * Sets reports Table View
     */
    public void setReportsTable() {
        customerIDTextField.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameTextField.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressTextField.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerZipTextField.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneTextField.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerStateProvinceText.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getDivisionName());
            } catch (SQLException | RuntimeException e) {
                errorHandler("ERROR: SQL", "Invalid foreign key", "Error retrieving the state or province name.");
            }
            System.exit(0);
            return null;
        });
    }
    /**
     * Initializes reports.
     * Sets the reports tables using DAOs.
     * Sets the combo boxes using DAOs.
     * Sets the prompt text for the combo box.
     */
    public void initialize() throws SQLException {
        setReportsTable();
        reportsComboBox.setItems(customerDAO.getDistinctPostalCodes());
        reportsComboBox.setPromptText("Select a Postal Code");
    }

}
