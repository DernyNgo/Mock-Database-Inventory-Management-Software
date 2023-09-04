package Java.Controllers;

import Java.Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

/**
 * @author Daniel Ngo
 * Controller for methods regarding times, dates, localdatetimes, zoneddatetime.
 * Includes error and confirmation handlers.
 */

public abstract class MethodController {
    /**
     * References stage and scenes.
     */
    public Stage stage;
    public Scene scene;
    public Parent root;
    /**
     * Instantiates Eastern Standard Time.
     */
    public static ZoneId EasternZoneID = ZoneId.of("America/New_York");
    /**
     * Instantiates Default Time.
     */
    public static ZoneId defaultZoneID = ZoneId.systemDefault();
    /**
     * Method to convert Local time to Eastern Standard Time.
     * @return Returns local time with EST constant.
     * @param time Time reference
     */
    public ZonedDateTime convertLocalToEST(LocalDateTime time) {
        return time.atZone(defaultZoneID).withZoneSameInstant(EasternZoneID);
    }
    /**
     * Creates Error Handlers to remove redundancy in code.
     * @param content Content text
     * @param header Header text
     * @param title Title text
     */
    public void errorHandler(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    /**
     * Creates Confirmation Handlers to remove redundancy in code.
     * @param content Content text
     * @param header Header text
     * @param title Title text
     * @return returns Button cancel confirmation
     */
    public boolean confirmationHandler(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
        return alert.getResult() == ButtonType.CANCEL;
    }
    /**
     * Creates ObservableList to store translation variables
     * @param rb Resource Bundle
     * @param text Input Text
     * @return Returns translation text
     */
    public ObservableList<String> translate(ObservableList<String> text, ResourceBundle rb) {
        ObservableList<String> translationText = FXCollections.observableArrayList();
        for (String str : text) {
            translationText.add(rb.getString(str));
        }
        return translationText;
    }

    /**
     * Loads the scene specified by the scene name.
     * @param event Switches scenes using On Action event
     * @param sceneName The name of the FXML to load
     */
    @FXML
    protected void sceneChange(ActionEvent event, String sceneName) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(sceneName));
        stage.setScene(new Scene(root));
        Main.setLocation(sceneName);
        stage.show();
    }
}
