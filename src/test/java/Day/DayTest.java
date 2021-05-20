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

    @Test
    public void testSleepEquals(){
        Day day1 = new Day();
        Day day2 = new Day();

        day1.setSleep(8);
        day2.setSleep(8);

        var result = day1.equals(day2);
        Assert.assertTrue(result);
    }

    @Test
    public void testWeightEquals(){
        Day day1 = new Day();
        Day day2 = new Day();

        day1.setWeight(76);
        day2.setWeight(76);

        var result = day1.equals(day2);
        Assert.assertTrue(result);
    }

    @Test
    public void testCreatineEquals(){
        Day day1 = new Day();
        Day day2 = new Day();

        day1.setCreatine(true);
        day2.setCreatine(true);

        var result = day1.equals(day2);
        Assert.assertTrue(result);
    }

    @Test
    public void testStretchEquals(){
        Day day1 = new Day();
        Day day2 = new Day();

        day1.setStretch(false);
        day2.setStretch(false);

        var result = day1.equals(day2);
        Assert.assertTrue(result);
    }

    @Test
    public void testWrkEquals(){
        Day day1 = new Day();
        Day day2 = new Day();

        day1.setWrk("mell");
        day2.setWrk("mell");

        var result = day1.equals(day2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashEquals(){
        Day day1 = new Day();
        Day day2 = new Day();

        day1.setDate("05.15");
        day2.setDate("05.15");

        Assert.assertEquals(day1.hashCode(), day2.hashCode());
    }

    @Test
    public void testHashNotEquals(){
        Day day1 = new Day();
        Day day2 = new Day();

        day1.setDate("05.18");
        day2.setDate("05.15");

        Assert.assertFalse(day1.hashCode() == day2.hashCode());
    }
}
