package application;

import Day.DayRepo;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Class which contains counter methods.
 */
@Data
public class Counter {
    double bmi;
    double bmr;
    double avg;

    /**
     * Method that counts BMI value from the input parameters.
     * @param height means the person's height.
     * @param weight means the person's weight.
     * @return the preson's BMI index.
     */
    public static double bmiCount(double height, int weight){
        double BMI = 0;
        double heightInM = height/100;
        BMI = weight/(heightInM*heightInM);
        return BMI;
    }

    /**
     * Method that counts BMR value from the input parameters. BMR value means how much calories should you eat a day.
     * @param height means the person's height.
     * @param weight means the person's weight.
     * @param sex means the person's sex.
     * @param age means the person's age.
     * @return the person's BMR value.
     */
    public static double bmrCount(double height, int weight, String sex, int age){
        double BMR = 0;
        if(sex.equals("Férfi"))
            BMR = 66.47+(13.75*weight)+(5.003*height)-(6.755*age);
        else if(sex.equals("Nő"))
            BMR = 655.1+(9.563*weight)+(1.85*height)-(4.676*age);
        return BMR;
    }

    /**
     * Method that counts average weight from the input file.
     * @param mainFile means the file we read from.
     * @return a double as result.
     * @throws IOException
     */
    public static double avgWeight(File mainFile) throws IOException {
        double result =0;
        if(mainFile == null)
            AppController.error();
        else{
            final var days = new DayRepo(mainFile.getPath()).getAll();

            result = days.stream()
                    .mapToDouble(day -> day.getWeight())
                    .average()
                    .getAsDouble();

        }
        return result;
    }

    /**
     *
     * @return a hash code value for the bmi.
     */
    @Override
    public int hashCode() {
        return Objects.hash(bmi);
    }

    /**
     *
     * @param o means the object.
     * @return that the two objects are equals or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Counter counter = (Counter) o;
        return Double.compare(counter.bmi, bmi) == 0 && Double.compare(counter.bmr, bmr) == 0 && Double.compare(counter.avg, avg) == 0;
    }
}
