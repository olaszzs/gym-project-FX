package Day;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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