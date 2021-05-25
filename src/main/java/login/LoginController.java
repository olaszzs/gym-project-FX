package login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField unField;
    @FXML
    private PasswordField pwField;
    @FXML
    private Button loginButton;


    /**
     * Method for log in, open the main stage or handle errors with an error message.
     * @throws IOException we need this because FXMLLoader.load might give an exception
     */
    private void login() throws IOException {
        if(unField.getText().equals("user") && pwField.getText().equals("pass")) {
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui.fxml"));
            mainStage.setTitle("Edzős program");
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.setResizable(false);
            mainStage.getIcons().add(new Image(getClass().getResource("/gym.png").toString()));
            mainStage.show();

        }
        else if(unField.getText().isEmpty() || pwField.getText().isEmpty()){
            Alert emptyError = new Alert(Alert.AlertType.ERROR);
            emptyError.setTitle("Hiba");
            emptyError.setHeaderText(null);
            emptyError.setContentText("Töltsd ki mindkét mezőt!");
            Stage stage = (Stage) emptyError.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResource("/error.png").toString()));
            emptyError.showAndWait();
        }
        else{
            Alert loginError = new Alert(Alert.AlertType.ERROR);
            loginError.setTitle("Hiba");
            loginError.setHeaderText(null);
            loginError.setContentText("Hibás felhasználónév vagy jelszó!");
            Stage stage = (Stage) loginError.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResource("/error.png").toString()));
            loginError.showAndWait();
        }
    }

    /**
     * Method for run login method on click Login button
     * @throws IOException we might have some exception because of the login method
     */
    @FXML
    private void onLogin() throws IOException {
        login();

    }

    /**
     * Method for run login method on press ENTER
     * @param e is an event we use to see which key was pressed
     * @throws IOException we might have some exception because of the login method
     */
    @FXML
    private void onEnter(KeyEvent e) throws IOException {
        if(e.getCode() == KeyCode.ENTER)
            login();
    }
}
