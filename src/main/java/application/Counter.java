package application;

/**
 * Class which contains counter methods.
 */
public class Counter {

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
}
