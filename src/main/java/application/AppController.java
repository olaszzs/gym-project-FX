package application;

import Day.Day;
import Day.DayRepo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.fxml.Initializable;


import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.tinylog.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AppController implements Initializable{

    @FXML
    private GridPane grdpn;
    @FXML
    private TextField dateField;
    @FXML
    private TextField wrkField;
    @FXML
    private TextField weightField;
    @FXML
    private CheckBox creatineCheckBox;
    @FXML
    private CheckBox stretchCheckBox;
    @FXML
    private CheckBox proteinCheckBox;
    @FXML
    private CheckBox cVitaminCheckBox;
    @FXML
    private CheckBox jumboCheckBox;
    @FXML
    private Spinner<Integer> sleepSpinner;
    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24,0);

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb){
        sleepSpinner.setValueFactory(svf);
    }

    @FXML
    private void onQuit(ActionEvent event){
        Platform.exit();
    }

    @FXML
    private void onInfo(ActionEvent event){
        sleepSpinner.setValueFactory(svf);
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Info");
        about.setHeaderText("Edzős program");
        about.setContentText("""
            Java version: %s, %s
            JavaFX version: %s
            Created by: Zsolt Olasz
            2021.02.25
            """.formatted(System.getProperty("java.version"), System.getProperty("java.vendor"), System.getProperty("javafx.version")));
        about.showAndWait();
    }

    @FXML
    @SneakyThrows
    private void onSave() {
        final var days = new DayRepo().getAll();

        //TODO több kattintásra több értéket tudjak beírni, így egy nap tudjam megadni az előzőt is akár

        JSONArray arr = new JSONArray();
        arr.addAll(days);

        //date format error
        if(dateField.getText().length() <= 6 && dateField.getText().length() >= 5){
            try(FileWriter file = new FileWriter("src/main/resources/out.json", false)){

                Day nap = new Day(dateField.getText(), wrkField.getText(), Integer.parseInt(weightField.getText()),
                        creatineCheckBox.isSelected(), stretchCheckBox.isSelected(), jumboCheckBox.isSelected(),
                        proteinCheckBox.isSelected(), cVitaminCheckBox.isSelected(), (Integer) sleepSpinner.getValue());

                //days.add(nap);

                arr.add(nap);
                System.out.println(arr.toJSONString());
                file.write(arr.toJSONString()+" \n");

                file.flush();
                //file.close();
            }catch(IOException err){
                Logger.error(err);  //Logging error with Tinylog
            }
        }
        else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Hiba");
            error.setHeaderText("Edzős program");
            error.setContentText("""
                    Hibás dátum
                    Várt formátum: hó.nap""");
            error.showAndWait();
        }
    }

}
