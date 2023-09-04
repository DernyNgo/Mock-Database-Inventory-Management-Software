package Java.Controllers;

import Java.DAO.AppointmentDAO;
import Java.Models.Appointment;
import javafx.collections.FXCollections;
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
import java.util.HashMap;
import java.util.Optional;

/**
 * @author Daniel Ngo
 * Reports Month View Controller.
 * Extends Reports Controller.
 */

public class ReportsMonth extends Reports {
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
     * Combo box that takes in String data types.
     */
    @FXML
    private ComboBox<String> reportsComboBox;
    /**
     * Label for Reports Total Counter.
     */
    @FXML
    protected Label reportsTotalCounter;
    /**
     * Table View for reports using Appointment model.
     */
    @FXML
    private TableView<Appointment> reportsTableView;
    /**
     * References Appointment DAO.
     */
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    /**
     * Creates Hashmap to add Months into Combo Box.
     */
    protected HashMap<String, Integer> addMonths = new HashMap<>();
    /**
     * Creates Observable List to hold months.
     */
    protected ObservableList<String> months = FXCollections.observableArrayList();
    /**
     * Fills hashmap with months and corresponding keys for months.
     */
    private void fillHashMap() {
        addMonths.put("January", 1);
        addMonths.put("February", 2);
        addMonths.put("March", 3);
        addMonths.put("April", 4);
        addMonths.put("May", 5);
        addMonths.put("June", 6);
        addMonths.put("July", 7);
        addMonths.put("August", 8);
        addMonths.put("September", 9);
        addMonths.put("October", 10);
        addMonths.put("November", 11);
        addMonths.put("December", 12);
    }
    /**
     * Adds months to Observable List.
     */
    private void fillOL() {
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
    }
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
        ObservableList<Appointment> appointments = appointmentDAO.getAllAppointmentsMonthly(addMonths.get(reportsComboBox.getValue()));
        reportsTableView.setItems(appointments);
        reportsTotalCounter.setText(reportsComboBox.getValue() + " Appointments: " + appointments.size());
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
     * Fills Hash Map with data.
     * Fills Observable List with data.
     * Sets the reports tables using DAOs.
     * Sets the combo boxes using DAOs.
     * Sets the prompt text for the combo box.
     */
    public void initialize() throws SQLException {
        setReportsTable();
        fillHashMap();
        fillOL();
        reportsComboBox.setItems(months);
        reportsComboBox.setPromptText("Select a Month");
    }

}
