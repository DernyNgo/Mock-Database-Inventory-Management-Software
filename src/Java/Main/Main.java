package Java.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import Java.Helper.JDBC;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Daniel Ngo
 * The Main class for the Appointment Application.
 * Extends Application.
 * Gets and sets the user's location.
 * Gets and sets the user.
 */

public class Main extends Application {
    public static String user = null;
    public static String location = null;

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        Main.location = location;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Main.user = user;
    }
    /**
     * The Login Screen is initialized by this method.
     * @throws IOException Signals Exception for input and output occurrences.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Locale userLocale = Locale.getDefault();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Java/Views/LoginScreen.fxml"), ResourceBundle.getBundle("Java/Resources/lang", userLocale));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Appointment Application");
        stage.setScene(scene);
        setLocation("/Java/Views/LoginScreen.fxml");
        stage.show();
    }
    /**
     * Opens and closes the Database connection.
     * @param args Takes in parameter arguments
     */
    public static void main(String[] args) {

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();

    }
}