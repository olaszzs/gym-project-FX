package application;

import Day.Day;
import Day.DayRepo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.fxml.Initializable;


import javafx.stage.Stage;
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
        Stage stage = (Stage) about.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/info.png"));
        about.showAndWait();
    }


    @FXML
    @SneakyThrows
    private void onSave() {
        final var days = new DayRepo().getAll();
        for(int i=0; i<days.size(); i++)
            System.out.println(days.get(i));

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
                file.close();
            }catch(IOException err){
                Logger.error(err);  //Logging error with Tinylog
            }
        }
        else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Hiba");
            error.setHeaderText(null);
            error.setContentText("""
                    Hibás dátum
                    Várt formátum: hó.nap""");
            Stage stage = (Stage) error.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/error.png"));
            error.showAndWait();
        }
    }

    @FXML
    public void avgWeight() throws IOException {
        final var days = new DayRepo().getAll();

        /**
         * Error handling, if there is no data in the file, give an error message instead of crashing the program.
         * But if there is data in the file, perform the task.
         */
        if(days.isEmpty()){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Hiba");
            error.setHeaderText(null);
            error.setContentText("Nincs tárolt adat!");
            Stage stage = (Stage) error.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/error.png"));
            error.showAndWait();
        }
        else {
            final var result = days.stream()
                    .mapToDouble(day -> day.getWeight())
                    .average()
                    .getAsDouble();

            Alert avgW = new Alert(Alert.AlertType.INFORMATION);
            avgW.setTitle("Átlag testtömeg");
            avgW.setHeaderText(null);
            avgW.setContentText("""
                    Az átlag testtömeged az eddig vezetett napok alapján: 
                    """ + result + " kg");
            Stage stage = (Stage) avgW.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/biceps.png"));
            avgW.showAndWait();
        }
    }

    @FXML
    public void lessTrained() throws  IOException{
        final var days = new DayRepo().getAll();

        String tmp;
        /*final var result = days.stream()
                .map(day -> day.getWrk())
                .forEach();

        System.out.println(result);*/
    }

}
