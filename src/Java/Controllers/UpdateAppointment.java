package Java.Controllers;

import Java.DAO.AppointmentDAO;
import Java.Main.Main;
import Java.Models.Appointment;
import Java.Models.Contact;
import Java.Models.Customer;
import Java.Models.User;
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
 * Update Appointment Controller Screen
 * Extends Appointment Validation Controller
 */

public class UpdateAppointment extends ApptValidationController {
    /**
     * References stage and parent scenes.
     */
    Stage stage;
    Scene scene;
    Parent root;
    /**
     * Combo box using Contact model.
     */
    @FXML
    private ComboBox<Contact> contactComboBox;
    /**
     * Combo box using Customer model.
     */
    @FXML
    private ComboBox<Customer> customerComboBox;
    /**
     * Text field for Appointment Descriptions.
     */
    @FXML
    private TextField apptDescTextField;
    /**
     * Date Picker for end times.
     */
    @FXML
    private DatePicker endDatePicker;
    /**
     * Text field for Appointment IDs.
     */
    @FXML
    private TextField apptIDTextField;
    /**
     * Text field for Appointment Locations.
     */
    @FXML
    private TextField apptLocTextField;
    /**
     * Date Picker for starting times.
     */
    @FXML
    protected DatePicker startDatePicker;
    /**
     * Text field for Appointment Titles.
     */
    @FXML
    private TextField apptTitleTextField;
    /**
     * Text field for Appointment Types.
     */
    @FXML
    private TextField apptTypeTextField;
    /**
     * Combo box using User model.
     */
    @FXML
    private ComboBox<User> userComboBox;
    /**
     * References Appointment DAO.
     */
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    /**
     * Appointment instantiation.
     */
    private Appointment appointment;

    /**
     * Cancels user input and prompts the user to return to the appointment screen.
     * @param event Cancels add appointment using on Action
     * @throws IOException signals exception for input and output occurrences
     */
    @FXML
    void updateAppointmentCancelButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation:");
        alert.setContentText("Return to the appointment scheduler? Changes will not be saved.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            sceneChange(event, "/Java/Views/MainScreen.fxml");
        }
    }
    /**
     * Saves user input and returns the user to the appointment screen.
     * @param event Cancels add appointment using on Action
     * @throws IOException signals exception for input and output occurrences
     * @throws SQLException signals exception for SQL occurrences
     */
    @FXML
    void updateAppointmentSaveButton(ActionEvent event) throws IOException, SQLException {
        String title = apptTitleTextField.getText();
        String description = apptDescTextField.getText();
        String location = apptLocTextField.getText();
        String type = apptTypeTextField.getText();
        if (!validateFields(title, description, location, type)) {
            return;
        }
        LocalDateTime start = getLocalDateTime(startDatePicker.getValue(), spinnerStartHours.getValue(), spinnerStartMinutes.getValue());
        LocalDateTime end = getLocalDateTime(endDatePicker.getValue(), spinnerEndHours.getValue(), spinnerEndMinutes.getValue());
        if (!validateTimes(start, end)) {
            return;
        }
        int customer = customerComboBox.getValue().getCustomerID();
        int user = userComboBox.getValue().getUserID();
        int contact = contactComboBox.getValue().getContactID();
        if (!appointmentDAO.getOverlappedAppointmentsWithoutSelf(start, end, this.appointment.getAppointmentID(), customer).isEmpty()) {
            errorHandler("Date/Time Error", "Overlapping Appointments", "There is already an appointment scheduled during this time block");
            return;
        }
        Appointment appointment = new Appointment(this.appointment.getAppointmentID(), title, description, location, type, start, end,
                this.appointment.getCreateDate(), this.appointment.getCreatedBy(), LocalDateTime.now(), Main.getUser(),
                customer, user, contact);
        appointmentDAO.updateAppointment(appointment);
        sceneChange(event, "/Java/Views/MainScreen.fxml");
    }

    /**
     * Sets the appointment to be updated.
     * @param appointment Appointment to be updated.
     * @throws SQLException Signals exception for SQL occurrences.
     */
    public void setUpdatedAppointment(Appointment appointment) throws SQLException {
        this.appointment = appointment;
        apptIDTextField.setText(String.valueOf(appointment.getAppointmentID()));
        apptTitleTextField.setText(appointment.getTitle());
        apptDescTextField.setText(appointment.getDescription());
        apptLocTextField.setText(appointment.getLocation());
        apptTypeTextField.setText(appointment.getType());
        startDatePicker.setValue(appointment.getStart().toLocalDate());
        endDatePicker.setValue(appointment.getEnd().toLocalDate());
        comboBoxSetup();
        spinnerSetUp();
        spinnerStartHours.getValueFactory().setValue(appointment.getStart().getHour());
        spinnerStartMinutes.getValueFactory().setValue(appointment.getStart().getMinute());
        spinnerEndHours.getValueFactory().setValue(appointment.getEnd().getHour());
        spinnerEndMinutes.getValueFactory().setValue(appointment.getEnd().getMinute());
        customerComboBox.setValue(customerDAO.getCustomer(appointment.getCustomerID()));
        userComboBox.setValue(userDAO.getUser(String.valueOf(appointment.getUserID())));
        contactComboBox.setValue(contactDAO.getContact(appointment.getContactID()));
    }
}

