package Day;

/**
 * Import necessarry annotations.
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * @param date means the date you want to write about
 * @param wrk means the trained muscle group
 * @param weight means your weight this day
 * @param creatine means drank creatine this day or not
 * @param stretch means stretched your muscles or not
 * @param jumbo means drank jumbo mass gainer this day or not
 * @param protein means drank protein shake this day or not
 * @param cvitamin means get daily c-vitamin dose this day or not
 * @param sleep means the hours you slept this day
 */
public class Day {
    String date;
    String wrk;         //the trained muscle group
    int weight;
    boolean creatine;
    boolean stretch;
    boolean jumbo;      //used jumbo as a mass gainer
    boolean protein;    //used extra protein as a shake
    boolean cvitamin;
    int sleep;

    /**
     * @return modified toString() to this class.
     */
    @Override
    public String toString() {
        return "{" +
                "\"date\":" + '\"' + date + '\"' +
                ",\"wrk\":" + '\"' + wrk + '\"' +
                ",\"weight\":" + '\"' + weight + '\"'+
                ",\"creatine\":" + '\"' + creatine + '\"' +
                ",\"stretch\":" + '\"' + stretch + '\"' +
                ",\"jumbo\":" + '\"' + jumbo + '\"' +
                ",\"protein\":" + '\"' + protein + '\"' +
                ",\"cvitamin\":" + '\"' + cvitamin + '\"' +
                ",\"sleep\":" + '\"' + sleep + '\"'+
                "}\n";
    }
}