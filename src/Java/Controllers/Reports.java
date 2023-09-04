package Java.Controllers;

import Java.DAO.AppointmentDAO;
import Java.DAO.ContactDAO;
import Java.Models.Appointment;
import Java.Models.Contact;
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
 * Reports View for the application.
 * Extends the Method Controller.
 */

public class Reports extends MethodController {
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
    protected ComboBox<Contact> reportsComboBox;
    /**
     * Table View for reports using Appointment model.
     */
    @FXML
    private TableView<Appointment> reportsTableView;
    /**
     * References Contact DAO.
     */
    private ContactDAO contactDAO = new ContactDAO();
    /**
     * References Appointment DAO.
     */
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
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
        ObservableList<Appointment> appointments = appointmentDAO.getAllAppointmentContactIDs(reportsComboBox.getValue().getContactID());
        reportsTableView.setItems(appointments);
        reportsTotalCounter.setText("Total Appointments: " + appointments.size());
    }
    /**
     * Sets reports Table View
     */
    public void setReportsTable() {
        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointmentEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointmentCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }
    /**
     * Initializes reports.
     * Sets the report tables using DAOs.
     * Sets the total appointments counter by appointment size.
     * @throws SQLException Signals exception for SQL occurrences
     */
    protected void reportsInitialize() throws SQLException {
        setReportsTable();
        ObservableList<Appointment> appointments = appointmentDAO.getAllAppointmentsByDate();
        reportsTableView.setItems(appointments);
        reportsTotalCounter.setText("Total Appointments: " + appointments.size());
    }
    /**
     * Initializes reports.
     * Sets the reports tables using DAOs.
     * Sets the combo boxes using DAOs.
     * Sets the prompt text for the combo box.
     */
    public void initialize() throws SQLException {
        //reportsInitialize();
        setReportsTable();
        reportsComboBox.setItems(contactDAO.getAllContacts());
        reportsComboBox.setPromptText("Select a Contact");
    }

}
