package Day;

/**
 * Import necessarry annotations.
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Using Lombok annotations for day class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Day {
    /**
     * means the date you want to write about.
     */
    String date;
    /**
     * means the trained muscle group.
     */
    String wrk;
    /**
     * means your weight this day.
     */
    int weight;
    /**
     * means drank creatine this day or not.
     */
    boolean creatine;
    /**
     * means stretched your muscles or not.
     */
    boolean stretch;
    /**
     * means drank jumbo mass gainer this day or not.
     */
    boolean jumbo;
    /**
     * means drank protein shake this day or not.
     */
    boolean protein;
    /**
     * means get daily c-vitamin dose this day or not.
     */
    boolean cvitamin;
    /**
     * means the hours you slept this day.
     */
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

    /**
     *
     * @param o means the object.
     * @return that the two objects are equals or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return Objects.equals(date, day.date);
    }

    /**
     *
     * @return a hash code value for the date.
     */
    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}