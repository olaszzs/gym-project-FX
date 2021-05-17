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
import java.util.*;
import java.util.stream.Collectors;


public class AppController implements Initializable{

    //List<Day> days = new DayRepo().getAll();

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

    public AppController() throws IOException {
    }

    /**
     * Overriding initialize method.
     * @param url The url used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The rb used to localize the root object, or null if the root object was not localized.
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb){
        sleepSpinner.setValueFactory(svf);
    }

    /**
     * Method for quit button.
     */
    @FXML
    private void onQuit(){
        Platform.exit();
    }

    /**
     * Method for info button.
     */
    @FXML
    private void onInfo(){
        sleepSpinner.setValueFactory(svf);
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Info");
        about.setHeaderText("Edzős program");
        about.setContentText("Java version: %s, %s\nJavaFX version: %s\nCreated by: Zsolt Olasz\n2021.05.17\n ".formatted(System.getProperty("java.version"), System.getProperty("java.vendor"), System.getProperty("javafx.version")));
        Stage stage = (Stage) about.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/info.png"));
        about.showAndWait();
    }


    /**
     * Method for save button.
     * Reads days from our json file and adds a new line to that if everything is fine.
     * There is an error handling, if the date format is not good, the program gives and error message.
     */
    @FXML
    @SneakyThrows
    private void onSave() {
        final var days = new DayRepo().getAll();

        JSONArray arr = new JSONArray();
        arr.addAll(days);

        //date format error
        if(dateField.getText().length() <= 6 && dateField.getText().length() >= 5){
            try(FileWriter file = new FileWriter("src/main/resources/days.json", false)){

                Day nap = new Day(dateField.getText(), wrkField.getText(), Integer.parseInt(weightField.getText()),
                        creatineCheckBox.isSelected(), stretchCheckBox.isSelected(), jumboCheckBox.isSelected(),
                        proteinCheckBox.isSelected(), cVitaminCheckBox.isSelected(), (Integer) sleepSpinner.getValue());

                days.add(nap);

                arr.add(nap);
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
            error.setContentText("Hibás dátum. Várt formátum: hó.nap");
            Stage stage = (Stage) error.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("src/main/resources/error.png"));
            error.showAndWait();
        }
    }

    /**
     * Method for average weight button.
     * There is an error handling, if there is no data in the file, give an error message instead of crashing the program.
     * But if there is data in the file, perform the task.
     */
    @FXML
    public void avgWeight() throws IOException {
        final var days = new DayRepo().getAll();

        if(days.isEmpty()){
            error();
        }
        else {
            final var result = days.stream()
                    .mapToDouble(day -> day.getWeight())
                    .average()
                    .getAsDouble();

            Alert avgW = new Alert(Alert.AlertType.INFORMATION);
            avgW.setTitle("Átlag testtömeg");
            avgW.setHeaderText(null);
            avgW.setContentText("Az átlag testtömeged az eddig vezetett napok alapján: \n"+ result + " kg");
            Stage stage = (Stage) avgW.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/biceps.png"));
            avgW.showAndWait();
        }
    }

    /**
     * Method for average weight button.
     * There is an error handling, if there is no data in the file, give an error message instead of crashing the program.
     * But if there is data in the file, perform the task.
     * @throws IOException
     */
    @FXML
    public void lessTrained() throws  IOException{
        final var days = new DayRepo().getAll();

        if(days.isEmpty()){
            error();
        }
        else {

            Map<String, Long> count = days.stream()
                    .collect(Collectors.groupingBy(day -> day.getWrk(), Collectors.counting()));

            final var result = (count.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey()).toUpperCase();

            Alert lessT = new Alert(Alert.AlertType.INFORMATION);
            lessT.setTitle("Lemaradás");
            lessT.setHeaderText(null);
            lessT.setContentText("Legkevesebbszer megedzett izomcsoport az eddig vezetett napok alapján: " + result);
            Stage stage = (Stage) lessT.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/biceps.png"));
            lessT.showAndWait();
        }
    }

    /**
     * Void method for error Alert.
     */
    public void error() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Hiba");
        error.setHeaderText(null);
        error.setContentText("Nincs tárolt adat!");
        Stage stage = (Stage) error.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/error.png"));
        error.showAndWait();
    }

}
