package application;

import Day.Day;
import Day.DayRepo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.fxml.Initializable;


import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Data;
import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.tinylog.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class AppController implements Initializable{

    ObservableList<String> sexChoiceList = FXCollections.observableArrayList("Férfi", "Nő");

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
    private ChoiceBox<String> sexChoiceBox ;
    @FXML
    private TextField heightField;
    @FXML
    private TextField ageField;

    File mainFile;
    DecimalFormat df2 = new DecimalFormat("#.##");

    public AppController()  {
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
        sexChoiceBox.getItems().addAll(sexChoiceList);
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
        stage.getIcons().add(new Image(getClass().getResource("/info.png").toString()));
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
        if(this.mainFile == null)
            error();

        else {
            final var days = new DayRepo(mainFile.getPath()).getAll();

            JSONArray arr = new JSONArray();
            arr.addAll(days);

            //date format error
            if (dateField.getText().length() <= 6 && dateField.getText().length() >= 5) {
                try (FileWriter file = new FileWriter(mainFile.getPath(), false)) {

                    Day nap = new Day(dateField.getText(), wrkField.getText(), Integer.parseInt(weightField.getText()),
                            creatineCheckBox.isSelected(), stretchCheckBox.isSelected(), jumboCheckBox.isSelected(),
                            proteinCheckBox.isSelected(), cVitaminCheckBox.isSelected(), (Integer) sleepSpinner.getValue());

                    days.add(nap);

                    arr.add(nap);
                    file.write(arr.toJSONString() + " \n");


                    file.flush();
                    file.close();
                } catch (IOException err) {
                    Logger.error(err);  //Logging error with Tinylog
                }
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Hiba");
                error.setHeaderText(null);
                error.setContentText("Hibás dátum. Várt formátum: hó.nap");
                Stage stage = (Stage) error.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResource("/error.png").toString()));
                error.showAndWait();
            }
        }
    }

    @FXML
    private void onOpen() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        File file = fileChooser.showOpenDialog(null);
        mainFile = file;
        if (file != null) {
            Logger.debug("Opening file {}", file);
            try {
                DayRepo asd = new DayRepo(file.getPath());
            } catch (IOException e) {
                Logger.error("Failed to open file");
            }
        }
        if(mainFile.length() == 0) {
            System.out.println("asd");
            FileWriter start = new FileWriter(mainFile.getPath());
            start.write("[]");
            start.close();
        }
    }

    /**
     * Method for average weight button.
     * There is an error handling, if there is no data in the file, give an error message instead of crashing the program.
     * But if there is data in the file, perform the task.
     */
    @FXML
    public void avgWeight() throws IOException {
        if(this.mainFile == null)
            error();
        else{
            double avg = Counter.avgWeight(mainFile);

            Alert avgW = new Alert(Alert.AlertType.INFORMATION);
            avgW.setTitle("Átlag testtömeg");
            avgW.setHeaderText(null);
            avgW.setContentText("Az átlag testtömeged az eddig vezetett napok alapján: \n"+ df2.format(avg) + " kg");
            Stage stage = (Stage) avgW.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResource("/biceps.png").toString()));
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
        if(this.mainFile == null)
            error();

        else{
            final var days = new DayRepo(mainFile.getPath()).getAll();

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
                stage.getIcons().add(new Image(getClass().getResource("/biceps.png").toString()));
                lessT.showAndWait();
            }
        }
    }

    /**
     * Method for counting BMI and BMR values.
     */
    @FXML
    private void onCount(){
        if(heightField.getText().isEmpty() || weightField.getText().isEmpty() || ageField.getText().isEmpty() || sexChoiceBox.getValue().isEmpty()) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Hiba");
            error.setHeaderText(null);
            error.setContentText("Töltse ki az összes mezőt!");
            Stage stage = (Stage) error.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResource("/error.png").toString()));
            error.showAndWait();
        }
        else {

            double height = Double.parseDouble(heightField.getText());
            int weight = Integer.parseInt(weightField.getText());
            String sex = sexChoiceBox.getValue();
            int age = Integer.parseInt(ageField.getText());
            double bmi = Counter.bmiCount(height, weight);
            double bmr = Counter.bmrCount(height, weight, sex, age);

            Alert counter = new Alert(Alert.AlertType.INFORMATION);
            counter.setTitle("BMI / BMR");
            counter.setHeaderText(null);
            counter.setContentText("BMI értéked: " + df2.format(bmi) + "\nBMR értéked: " + df2.format(bmr) + " kcal");
            Stage stage = (Stage) counter.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResource("/biceps.png").toString()));
            counter.showAndWait();
        }
    }

    /**
     * Void method for error Alert.
     */
    public static void error() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Hiba");
        error.setHeaderText(null);
        error.setContentText("Nincs tárolt adat!");
        Stage stage = (Stage) error.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/error.png"));
        error.showAndWait();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppController that = (AppController) o;
        return Objects.equals(mainFile, that.mainFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainFile);
    }
}
