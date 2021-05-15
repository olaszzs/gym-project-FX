package Day;

import org.junit.Assert;
import org.junit.Test;

public class DayTest {

    @Test
    public void testEquals(){
        Day day1 = new Day();
        Day day2 = new Day();

        day1.setDate("05.15");
        day2.setDate("05.15");

        var result = day1.equals(day2);
        Assert.assertTrue(result);
    }

    @Test
    public void testNotEquals(){
        Day day1 = new Day();
        Day day2 = new Day();

        day1.setDate("05.13");
        day2.setDate("06.15");

        var result = day1.equals(day2);
        Assert.assertFalse(result);
    }
}
