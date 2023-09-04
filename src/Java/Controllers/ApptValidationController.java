package Java.Controllers;

import Java.DAO.*;
import Java.Models.Appointment;
import Java.Models.Contact;
import Java.Models.Customer;
import Java.Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * @author Daniel Ngo
 * Controller for Validating Data for other appointment controllers.
 * Extends the Method controller.
 */
public abstract class ApptValidationController extends MethodController {
    /**
     * References Customer DAO.
     */
    public CustomerDAO customerDAO = new CustomerDAO();
    /**
     * References User DAO.
     */
    public UserDAO userDAO = new UserDAO();
    /**
     * References Contact DAO.
     */
    public ContactDAO contactDAO = new ContactDAO();
    /**
     * References Appointment DAO.
     */
    public AppointmentDAO appointmentDAO = new AppointmentDAO();
    /**
     * Combo box using Contact model.
     */
    @FXML
    protected ComboBox<Contact> contactComboBox;
    /**
     * Combo box using Customer model.
     */
    @FXML
    protected ComboBox<Customer> customerComboBox;
    /**
     * Combo box using User model.
     */
    @FXML
    protected ComboBox<User> userComboBox;
    /**
     * Date Picker for start dates.
     */
    @FXML
    protected DatePicker startDatePicker;
    /**
     * Date picker for end dates.
     */
    @FXML
    protected DatePicker endDatePicker;
    /**
     * Text field for Appointment Titles.
     */
    @FXML
    protected TextField apptTitleTextField;
    /**
     * Text Field for Appointment Descriptions.
     */
    @FXML
    protected TextField apptDescTextField;
    /**
     * Text Field for Appointment Locations.
     */
    @FXML
    protected TextField apptLocTextField;
    /**
     * Text Field for Appointment Types.
     */
    @FXML
    protected TextField apptTypeTextField;

    @FXML TextField apptIDTextField;
    /**
     * Spinner for end hours.
     */
    @FXML
    protected Spinner<Integer> spinnerEndHours;
    /**
     * Spinner for end minutes.
     */
    @FXML
    protected Spinner<Integer> spinnerEndMinutes;
    /**
     * Spinner for start hours.
     */
    @FXML
    protected Spinner<Integer> spinnerStartHours;
    /**
     * Spinner for start minutes.
     */
    @FXML
    protected Spinner<Integer> spinnerStartMinutes;

    @FXML
    private Label titleError;

    @FXML
    private Label descriptionError;

    @FXML
    private Label typeError;

    @FXML
    private Label locationError;

    /**
     * Populates combo boxes with contact, customer, and user data.
     */
    public void comboBoxSetup() throws SQLException {
        contactComboBox.setItems(contactDAO.getAllContacts());
        customerComboBox.setItems(customerDAO.getAllCustomers());
        userComboBox.setItems(userDAO.getAllUsers());
    }
    /**
     * Handler method for spinners
     */
    public void spinnerHandler(Spinner<Integer> spinner) {
        spinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d+")) {
                spinner.getEditor().setText(oldValue);
            }
        });
    }
    /**
     * Method to instantiate spinner constraints.
     */
    public void spinnerSetUp() {
        spinnerStartHours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        spinnerStartMinutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        spinnerEndHours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        spinnerEndMinutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        spinnerHandler(spinnerStartHours);
        spinnerHandler(spinnerStartMinutes);
        spinnerHandler(spinnerEndHours);
        spinnerHandler(spinnerEndMinutes);
    }

    /**
     * Validates appointment data fields using boolean values.
     * @param title Appointment title text field validation.
     * @param description Appointment description text field validation.
     * @param location Appointment location text field validation.
     * @param type Appointment type text field validation.
     * @return Returns true if fields are valid, otherwise returns false
     */
    public boolean validateFields(String title, String description, String location, String type) {
        boolean validationBoolean = true;

        if (title.isEmpty()) {
            titleError.setText("Title is required");
            titleError.setStyle("-fx-text-fill: red");
            validationBoolean = false;
        } else {
            titleError.setText("Title");
        }
        if (description.isEmpty()) {
            descriptionError.setText("Description is required");
            descriptionError.setStyle("-fx-text-fill: red");
            validationBoolean = false;
        } else {
            descriptionError.setText("Description");
        }
        if (location.isEmpty()) {
            locationError.setText("Location is required");
            locationError.setStyle("-fx-text-fill: red");
            validationBoolean = false;
        } else {
            locationError.setText("Location");
        }
        if (type.isEmpty()) {
            typeError.setText("Type is required");
            typeError.setStyle("-fx-text-fill: red");
            validationBoolean = false;
        } else {
            typeError.setText("Type");
        }
        if (selectionValidation()) {return false;}
        return validationBoolean;
    }

    /**
     * Validates if any of the combo boxes, date pickers, or spinners are not selected.
     * @return Returns true if fields are not selected, otherwise returns false.
     */
    private boolean selectionValidation() {
        boolean unselected = false;

        if (customerComboBox.getValue() == null) {
            customerComboBox.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            customerComboBox.setStyle("");
        }
        if (userComboBox.getValue() == null) {
            userComboBox.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            userComboBox.setStyle("");
        }
        if (contactComboBox.getValue() == null) {
            contactComboBox.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            contactComboBox.setStyle("");
        }
        if (startDatePicker.getValue() == null) {
            startDatePicker.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            startDatePicker.setStyle("");
        }
        if (endDatePicker.getValue() == null) {
            endDatePicker.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            endDatePicker.setStyle("");
        }
        if (spinnerStartHours.getValue() == null) {
            spinnerStartHours.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            spinnerStartHours.setStyle("");
        }
        if (spinnerStartMinutes.getValue() == null) {
            spinnerStartMinutes.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            spinnerStartMinutes.setStyle("");
        }
        if (spinnerEndHours.getValue() == null) {
            spinnerEndHours.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            spinnerEndHours.setStyle("");
        }
        if (spinnerEndMinutes.getValue() == null) {
            spinnerEndMinutes.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            spinnerEndMinutes.setStyle("");
        }
        return unselected;
    }

    /**
     * Validates the time and date fields.
     * Guarantees times are set in the future and appointments cannot be done before they begin.
     * @param start The start time and date
     * @param end The end time and date
     * @return Returns true if all constraints are met, otherwise returns false
     */
    protected boolean validateTimes(LocalDateTime start, LocalDateTime end) {
        if (!start.isBefore(end)) {
            errorHandler("Time or Date Error", "Start times must be before end times.", "Please input valid times");
            return false;
        }
        if (start.isBefore(LocalDateTime.now())) {
            errorHandler("Time or Date Error", "Start time must be between business hours, and one day in advance.", "Please input a time in the future. Appointments cannot be scheduled last minute.");
            return false;
        }
        if (!businessHoursValidation(start)) {
            return false;}

        return true;
    }

    /**
     * Ensures that start times are within business hours (EST).
     * @param start The start time and date
     * @return Returns true if the start time is between business hours, otherwise returns false
     */
    private boolean businessHoursValidation(LocalDateTime start) {
        boolean isValid = true;

        ZonedDateTime startEST = convertLocalToEST(start);
        if (closedHoursValidation(startEST)) {
            errorHandler("Time or Date Error", "Starting time has to be within EST business hours.", "Select a start time between 8am and 10pm EST");
            isValid = false;
        }
        return isValid;
    }

    /**
     * Validates if the time is outside of business hours (8am - 10pm EST).
     * @param time The time to check
     * @return Returns true if inputted time is outside of business hours, otherwise returns false
     */
    private boolean closedHoursValidation(ZonedDateTime time) {
        return (time.getHour() < 8 || time.getHour() > 22 || (time.getHour() == 22 && time.getMinute() > 0));
    }

    /**
     * Method creates a LocalDateTime object using the Date Picker and Spinners.
     * @param date Date Picker date
     * @param hours Spinner Hours
     * @param minutes Spinner Minutes
     * @return Returns LocalDateTime object
     */
    protected LocalDateTime getLocalDateTime(LocalDate date, int hours, int minutes) {
        return date.atTime(hours, minutes);
    }
}
