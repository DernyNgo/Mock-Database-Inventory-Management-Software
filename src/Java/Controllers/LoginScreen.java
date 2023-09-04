package Java.Controllers;

import Java.DAO.AppointmentDAO;
import Java.DAO.UserDAO;
import Java.Main.Main;
import Java.Models.Appointment;
import Java.Models.User;
import Java.Utils.LoginActivityIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Daniel Ngo
 * Login Screen for the Appointment Application.
 * extends the Method Controller.
 */

public class LoginScreen extends MethodController {
    /**
     * References stage and parent scenes.
     */
    Stage stage;
    Parent scene;
    /**
     * Exit Button to close the application.
     */
    @FXML
    private Button exitButtonText;
    /**
     * Combo box for language selection.
     */
    @FXML
    private ComboBox<String> languageSelection;
    /**
     * Login Button text.
     */
    @FXML
    private Button loginButtonText;
    /**
     * Password Label.
     */
    @FXML
    private Label passwordLabel;
    /**
     * Password Field.
     */
    @FXML
    private PasswordField password;
    /**
     * Username Label.
     */
    @FXML
    private Label usernameLabel;
    /**
     * Username Text Field.
     */
    @FXML
    private TextField userName;
    /**
     * ZoneID Label
     */
    @FXML
    private Label zoneID;
    /**
     * References User DAO.
     */
    private UserDAO userDao = new UserDAO();
    /**
     * References Appointment DAO.
     */
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    /**
     * Locale used for default language.
     */
    private Locale userLocale = Locale.getDefault();
    /**
     * Writes login activity to txt file.
     * @param success Successful or failed login attempts
     * @param messageLog Messages displayed for failed login attempts
     * @param userName Username of the login attempt.
     */
    private void loginActivity(boolean success, String messageLog, String userName) {
        LoginActivityIO.writeFile("login_activity.txt", userName + "," + LocalDateTime.now()
        + "," + success + "," + messageLog);
    }
    /**
     * Button that logs in user to the application.
     * Translates to French depending on user locale or language selection.
     * If loggin in is successful, users are re-routed to the Appointment screen.
     * @param event User Login using On Action event
     * @throws IOException Signals exception from input and output occurrences.
     * @throws SQLException Signals exception from SQL occurrences.
     */
    @FXML
    void loginButton(ActionEvent event)  throws SQLException, IOException {
        String userName = this.userName.getText();
        String password = this.password.getText();
        User user = userDao.getUser(userName);
        if (user == null) {
            if (userLocale.getLanguage().equals("fr")) {
                errorHandler("Nom d'utilisateur invalide", "Utilisateur non trouvé", "Merci d'entrer un nom d'utilisateur valide");
            }
            else {
                errorHandler("Invalid Username", "User not found", "Please enter a valid username");
            }
            loginActivity(false, "User not found", userName);
            return;
        }
        if (!user.getPassword().equals(password)) {
            if (userLocale.getLanguage().equals("fr")) {
                errorHandler("Mot de passe incorrect", "Mot de passe incorrect", "Entrer un mot de passe valide s'il te plait");
            }
            else {
                errorHandler("Invalid password", "Password incorrect", "Please enter a valid password");
            }
            loginActivity(false, "Incorrect password", userName);
            return;
        }
        loginActivity(true, "Not Applicable", userName);
        Main.setUser(user.getUserName());
        appointmentAlerts();
        sceneChange(event, "/Java/Views/MainScreen.fxml");
    }
    /**
     * Alerts users whether they have an appointment within the next 15 minutes.
     * @throws SQLException Signals exception from SQL occurrences
     */
    private void appointmentAlerts() throws SQLException {
        ObservableList<Appointment> appointmentsIn15minutes = appointmentDAO.get15MinuteAppointments(LocalDateTime.now());
        if (appointmentsIn15minutes.isEmpty()) {
            confirmationHandler("Confirmation", "There are no appointments within 15 minutes", "Please wait until your appointment time");
        } else {
            String content = "There is an appointment within 15 minutes";
            for (Appointment appointment : appointmentsIn15minutes) {
                String readableDate = appointment.getStart().format(DateTimeFormatter.ofPattern("MM dd, yyyy | HH:mm"));
                content += "\nID: " + appointment.getAppointmentID() + " on " + readableDate;
            };
            confirmationHandler("Confirmation", "Appointment within 15 minutes", content);
        }
    }
    /**
     * Sets up the combo box for language selection.
     */
    public void comboBoxSetup() {
        if (userLocale.getLanguage().equals("fr")) {
            languageSelection.setValue("Français");
            languageSelection.setItems(FXCollections.observableArrayList("Anglais", "Français"));
        } else {
            languageSelection.setValue("English");
            languageSelection.setItems(FXCollections.observableArrayList("English", "French"));
        }
    }
    /**
     * Creates the language selection combo box.
     * Refreshes the view with translations to French if French is selected.
     * @throws IOException Signals exceptions from input and output occurrences.
     */
    @FXML
    void languageSelectionCombo(ActionEvent event) throws IOException {
        String language = languageSelection.getValue();
        Locale newLocale;
        if (language.equals("French") || language.equals("Français")) {
            newLocale = new Locale("fr", "FR");
            Locale.setDefault(newLocale);
        } else {
            newLocale = new Locale("en", "US");
            Locale.setDefault(newLocale);
        }
        stage = (Stage)((ComboBox)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Java/Views/LoginScreen.fxml"), ResourceBundle.getBundle("Java/Resources/lang", newLocale));
        stage.setScene(new Scene(root));
        stage.show();
    }
    /**
     * Prompts the user to exit the program.
     * @param event Exits the program using On Action event
     */
    @FXML
    void exitButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation:");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
    /**
     * Initializes the login screen.
     * Sets up the combo boxes.
     * Sets the Zone ID to system default.
     */
    public void initialize() {
        zoneID.setText(ZoneId.systemDefault().toString());
        comboBoxSetup();
    }
}
