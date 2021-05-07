package application;

import Day.Day;
import Day.DayRepo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.tinylog.Logger;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;


public class AppController {

    @FXML
    private GridPane grdpn;
    private TextField dateField;
    private TextField wrkField;
    private TextField weightField;
    private CheckBox creatineCheckBox;
    private CheckBox stretchCheckBox;
    private CheckBox proteinCheckBox;
    private CheckBox cVitaminCheckBox;
    private CheckBox jumboCheckBox;
    private Spinner sleepSpinner;



    @FXML
    private void onQuit(ActionEvent event){
        Logger.info("Terminating");
        Platform.exit();
    }

    @FXML
    private void onInfo(ActionEvent event){
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
            try(FileWriter asd = new FileWriter("src/main/resources/asd.json", false)){

                FileWriter file = new FileWriter("src/main/resources/out.json", false);
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
            JOptionPane.showMessageDialog(null, "Hibás dátum", "Hiba", JOptionPane.ERROR_MESSAGE);
        }
    }

}
