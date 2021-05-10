package login;

import application.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField unField;
    @FXML
    private PasswordField pwField;
    @FXML
    private Button loginButton;

    @FXML
    private void onLogin() throws IOException {
        if(unField.getText().equals("user") && pwField.getText().equals("pass")) {
            System.out.println("Login success");
        }
        else{
            Alert loginError = new Alert(Alert.AlertType.ERROR);
            loginError.setTitle("Hiba");
            loginError.setHeaderText(null);
            loginError.setContentText("Hibás felhasználónév vagy jelszó!");
            Stage stage = (Stage) loginError.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/error.png"));
            loginError.showAndWait();
        }

    }
}
