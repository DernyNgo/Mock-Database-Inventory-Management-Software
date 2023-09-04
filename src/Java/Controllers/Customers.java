package Java.Controllers;

import Java.DAO.AppointmentDAO;
import Java.DAO.CustomerDAO;
import Java.Main.Main;
import Java.Models.Appointment;
import Java.Models.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * @author Daniel Ngo
 * Main form for Customer Table View.
 * extends the Method Controller.
 * ****** LAMBDA FUNCTION USED HERE ON LINE 174 ******
 */
public class Customers extends MethodController {
    /**
     * References stage and parent scenes.
     */
    Stage stage;
    Parent scene;
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
    @FXML
    private TableView<Customer> customerTableView;
    /**
     * References Customer DAO.
     */
    protected CustomerDAO customerDAO = new CustomerDAO();
    /**
     * References Appointment DAO.
     */
    protected AppointmentDAO appointmentDAO = new AppointmentDAO();
    /**
     * Switches scene to Add Customers view.
     * @param event Adds customer using On Action
     * @throws IOException signals exception for input and output occurrences
     */
    @FXML
    void addCustomerAction(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Java/Views/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * Switches scene to Appointment view.
     * Prompts user to return to the appointment view.
     * @param event Switches to Appointment View using On Action
     * @throws IOException signals exception for input and output occurrences
     */
    @FXML
    void appointmentViewAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation:");
        alert.setContentText("Return to the appointment scheduler?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/Java/Views/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    /**
     * Deletes Customer from the database.
     * Prompts the user if a customer is about to be deleted.
     * Prompts the user if a customer is not selected.
     * Prompts the user if a database error has occurred.
     * @param event Deletes customer using On Action
     */
    @FXML
    void deleteCustomerAction(ActionEvent event) {
        if (customerTableView.getSelectionModel().getSelectedItem() == null) {
            errorHandler("No Customer Selection", "Deletion did not occur.", "Highlight a customer to be deleted.");
            return;
        }
        Customer customer = customerTableView.getSelectionModel().getSelectedItem();
        if (confirmationHandler("Delete Customer", "Delete " + customer.getCustomerName() + "?",
                "This action will also delete all appointments associated with the selected customer. This cannot be undone.")) {
            return;
        }
        try {
            customerDAO.deleteCustomer(customer.getCustomerID());
            customerTableView.setItems(customerDAO.getAllCustomers());
            //appointmentTableView.setItems(appointmentDAO.getAllAppointments());
            errorHandler("Customer deleted", "Customer " + customer.getCustomerName() + " has been deleted.",
                    "All associated appointments have also been deleted.");
        } catch (SQLException e) {
            errorHandler("Database error", "ERROR: ", "Customer deletion did not occur.");
        }
    }
    /**
     * Updates Customer from the database.
     * Prompts the user if a customer is not selected.
     * @param event Deletes customer using On Action
     * @throws IOException signals exception for input and output occurrences
     * @throws SQLException signals exception for SQL occurrences
     */
    @FXML
    void updateCustomerAction(ActionEvent event) throws IOException, SQLException {
        if (customerTableView.getSelectionModel().getSelectedItem() == null) {
            errorHandler("No Customer Selection", "Update did not occur.", "Select a customer to update.");
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Java/Views/UpdateCustomer.fxml"));
        loader.load();
        UpdateCustomer updateCustomer = loader.getController();
        updateCustomer.setCustomer(customerTableView.getSelectionModel().getSelectedItem());
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = loader.getRoot();
        stage.setScene(new Scene(root));
        Main.setLocation("/Java/Views/UpdateCustomer.fxml");
        stage.show();
    }
    /**
     * LAMBDA USAGE: Get Division Name from Division ID using the Customer Object.
     * Sets customer table view.
     * Exits the program if there is an SQL error.
     * @throws SQLException signals exception for SQL occurrences
     */
    private void setCustomerTableView() throws SQLException {
        customerTableView.setItems(customerDAO.getAllCustomers());
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

    public void initialize() throws SQLException {
        setCustomerTableView();
    }
}
