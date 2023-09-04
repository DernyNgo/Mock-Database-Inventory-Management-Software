package Java.Controllers;

import Java.Main.Main;
import Java.Models.Appointment;
import Java.Models.Contact;
import Java.Models.Customer;
import Java.Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Daniel Ngo
 * Controller for Adding Appointments.
 * Adds appointments into the database.
 * extends the Appointment Validation Controller.
 * ****** LAMBDA FUNCTION USED HERE ON LINE 98 ******
 */
public class AddAppointment extends ApptValidationController {
    /**
     * References stage and parent scenes.
     */
    Stage stage;
    Parent scene;

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
     * Date Picker for end times.
     */
    @FXML
    private DatePicker endDatePicker;
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
     * LAMBDA USAGE: Cancels user input and prompts the user to return to the appointment screen.
     * Throws RuntimeException for runtime occurrences.
     * @param event Cancels add appointment using on Action
     */
    @FXML
    void addAppointmentCancelButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation:");
        alert.setContentText("Return to the appointment scheduler? Changes will not be saved.");
        Optional<ButtonType> result = alert.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                try {
                    sceneChange(event, "/Java/Views/MainScreen.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Saves user input and returns the user to the appointment screen.
     * @param event Cancels add appointment using on Action
     * @throws IOException signals exception for input and output occurrences
     * @throws SQLException signals exception for SQL occurrences
     */
    @FXML
    void addAppointmentSaveButton(ActionEvent event) throws SQLException, IOException {

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
        if (!appointmentDAO.getOverlappedAppointments(start, end, customer).isEmpty()) {
            errorHandler("Date or Time Error", "Overlapping Appointments", "Appointment already scheduled during this time frame");
            return;
        }
        Appointment appointment = new Appointment(1111, title, description, location, type, start, end,
                LocalDateTime.now(), Main.getUser(), LocalDateTime.now(), Main.getUser(),
                customer, user, contact);
        appointmentDAO.addAppointment(appointment);
        sceneChange(event, "/Java/Views/MainScreen.fxml");
    }

    public void initialize() throws SQLException {
        comboBoxSetup();
        spinnerSetUp();
        //loadAppointmentID();
    }
}

