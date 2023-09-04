package Java.Controllers;

import Java.DAO.AppointmentDAO;
import Java.Main.Main;
import Java.Models.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Daniel Ngo
 * Main Appointment Screen for the application.
 * Extends the Appointment Validation Controller.
 */

public class MainScreen extends ApptValidationController {
    /**
     * References stage and parent scenes.
     */
    Stage stage;
    Scene scene;
    Parent root;
    /**
     * Table Column for Appointment Contacts.
     */
    @FXML
    private TableColumn<Appointment, String> appointmentContactColumn;
    /**
     * Table Column for Appointment Customer IDs.
     */
    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomerIDColumn;
    /**
     * Table Column for Appointment Description.
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
     * Table View for Appointments.
     */
    @FXML
    private TableView<Appointment> appointmentTableView;
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
     * Table Column for Appointment User IDs.
     */
    @FXML
    private TableColumn<Appointment, Integer> appointmentUserIDColumn;
    /**
     * Date Picker for Appointments.
     */
    @FXML
    private DatePicker apptDatePicker;
    /**
     * Toggle Group for Radio Buttons.
     */
    @FXML
    private ToggleGroup viewToggleGroup;
    /**
     * Radio Button to View All Appointments.
     */
    @FXML
    private RadioButton viewAllRadioButton;
    /**
     * Radio Button to View Appointments by Month.
     */
    @FXML
    private RadioButton viewMonthRadioButton;
    /**
     * Radio Button to View Appointments by Week.
     */
    @FXML
    private RadioButton viewWeekRadioButton;

    /**
     * Method to switch screens to Add Appointments.
     * @param event switches screens using On Action event
     * @throws IOException Signals exception for input and output occurrences
     */
    @FXML
    void addAppointmentAction(ActionEvent event) throws IOException {
        sceneChange(event, "/Java/Views/AddAppointment.fxml");
    }

    /**
     * Method to switch screens to Customer View.
     * @param event switches screens using On Action event
     * @throws IOException Signals exception for input and output occurrences
     */
    @FXML
    void customerViewAction(ActionEvent event) throws IOException {
        sceneChange(event, "/Java/Views/Customers.fxml");
    }
    /**
     * Method to delete appointments.
     * Prompts users if an appointment is not selected.
     * Prompts users if cancellation occurs.
     * Prompts users if there are database errors.
     * @param event deletes appointments using On Action event
     */
    @FXML
    void deleteAppointmentAction(ActionEvent event) {
        if (appointmentTableView.getSelectionModel().getSelectedItem() == null) {
            errorHandler("Appointment not selected", "Please select an appointment to delete", "Please try again!");
            return;
        }
        Appointment appointment = appointmentTableView.getSelectionModel().getSelectedItem();
        if (confirmationHandler("Cancel Appointment", "Are you sure you want to cancel " + appointment.getTitle() + "?",
                "This action cannot be undone.")) {
            return;
        }
        try {
            appointmentDAO.deleteAppointment(appointment.getAppointmentID());
            appointmentTableView.setItems(appointmentDAO.getAllAppointments());
            errorHandler("Appointment cancelled", "Appointment " + appointment.getTitle() + " has been cancelled.",
                    "Appointment ID: " + appointment.getAppointmentID() + " Type: " + appointment.getType() + " has been cancelled.");
        } catch (SQLException e) {
            errorHandler("Database error", "Could not delete appointment", "Please try again.");
        }
    }
    /**
     * Method to switch screens to Log in screen.
     * Prompts users if they want to log out of the program.
     * Logs users out of the appointment application.
     * @param event switches screens using On Action event
     * @throws IOException Signals exception for input and output occurrences
     */
    @FXML
    void logOutAction(ActionEvent event) throws IOException {
        Locale userLocale = Locale.getDefault();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation:");
        alert.setContentText("Log out of the program?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/Java/Views/LoginScreen.fxml"), ResourceBundle.getBundle("Java/Resources/lang", userLocale));
            stage.setScene(new Scene(root));
            Main.setUser(null);
            Main.setLocation("/Java/Views/LoginScreen.fxml");
            stage.show();
        }
    }
    /**
     * Method to switch screens to Reports.
     * @param event switches screens using On Action event
     * @throws IOException Signals exception for input and output occurrences
     */
    @FXML
    void reportsViewAction(ActionEvent event) throws IOException {
        sceneChange(event, "/Java/Views/Reports.fxml");
    }
    /**
     * Method to switch screens to Update Appointments.
     * Prompts users if an appointment is not selected.
     * @param event switches screens using On Action event
     * @throws IOException Signals exception for input and output occurrences
     * @throws SQLException Signals exception for SQL occurrences
     */
    @FXML
    void updateAppointmentAction(ActionEvent event) throws SQLException, IOException {
        if (appointmentTableView.getSelectionModel().getSelectedItem() == null) {
            errorHandler("Appointment not selected", "Please select an appointment to update", "Please try again!");
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Java/Views/UpdateAppointment.fxml"));
        loader.load();
        UpdateAppointment updateAppointment = loader.getController();
        updateAppointment.setUpdatedAppointment(appointmentTableView.getSelectionModel().getSelectedItem());
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = loader.getRoot();
        stage.setScene(new Scene(root));
        Main.setLocation("/Views/UpdateAppointment.fxml");
        stage.show();
    }
    /**
     * Sets disable values of Date Picker to false if no selection is present.
     * @param event sets false using On Action event
     */
    @FXML
    void apptDatePickerAction(ActionEvent event) {
        if (apptDatePicker.getValue() == null) {
            viewWeekRadioButton.setDisable(false);
            viewMonthRadioButton.setDisable(false);
        } else {
            viewWeekRadioButton.setDisable(false);
            viewMonthRadioButton.setDisable(false);
        }
    }
    /**
     * Radio button selection to View All Appointments.
     * @param event populates tableview using On Action event
     * @throws SQLException Signals exception for SQL occurrences
     */
    @FXML
    void viewAllAction(ActionEvent event) throws SQLException {
        apptDatePicker.setValue(null);
        appointmentTableView.setItems(appointmentDAO.getAllAppointments());
    }
    /**
     * Radio button selection to View Appointments by Month.
     * @param event populates tableview using On Action event
     * @throws SQLException Signals exception for SQL occurrences
     */
    @FXML
    void viewMonthAction(ActionEvent event) throws SQLException {
        if (apptDatePicker.getValue() == null) {
            errorHandler("Date error", "No date selected", "Please select a date to filter by");
            return;
        }
        appointmentTableView.setItems(appointmentDAO.getMonthlyAppointments(apptDatePicker.getValue()));
    }
    /**
     * Radio button selection to View Appointments by Week.
     * @param event populates tableview using On Action event
     * @throws SQLException Signals exception for SQL occurrences
     */
    @FXML
    void viewWeekAction(ActionEvent event) throws SQLException {
        if (apptDatePicker.getValue() == null) {
            errorHandler("Date error", "No date selected", "Please select a date to filter by");
            return;
        }
        appointmentTableView.setItems(appointmentDAO.getWeeklyAppointments(apptDatePicker.getValue()));
    }
    /**
     * Sets Appointment View Table.
     * @throws SQLException Signals exception for SQL occurrences
     */
    public void setAppointmentsTable() throws SQLException {
        appointmentTableView.setItems(appointmentDAO.getAllAppointments());
        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointmentEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointmentCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appointmentUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }
    /**
     * Initializes Appointment View Screen.
     * Sets Appointment Table.
     * @throws SQLException Signals exception for SQL occurrences
     */
    public void initialize() throws SQLException {
        setAppointmentsTable();
    }
}

