package application;

import org.junit.Assert;
import org.junit.Test;

public class CounterTest {

    @Test
    public void TestBmiEquals(){
        double bmi1 = Counter.bmiCount(180, 76);
        double bmi2 = Counter.bmiCount(180, 76);
        double bmi3 = Counter.bmiCount(180, 76);

        Assert.assertEquals(bmi1, bmi2, bmi3);
    }

    @Test
    public void TestBmrEquals(){
        double bmr1 = Counter.bmrCount(180, 76, "Férfi", 20);
        double bmr2 = Counter.bmrCount(180, 76, "Férfi", 20);
        double bmr3 = Counter.bmrCount(180, 76, "Férfi", 20);

        Assert.assertEquals(bmr1, bmr2, bmr3);
    }

    @Test
    public void TestBmiHashEquals(){
        Counter c1 = new Counter();
        c1.setBmi(23.5);

        Assert.assertEquals(c1.hashCode(), c1.hashCode());
    }

    @Test
    public void TestBmiHashNotEquals(){
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        c1.setBmi(23.5);
        c2.setBmi(25.5);

        Assert.assertFalse(c1.hashCode() == c2.hashCode());
    }

    @Test
    public void TestEquals(){
        Counter c1 = new Counter();
        Counter c2 = new Counter();

        c1.setBmi(23.5);
        c2.setBmi(23.5);
        c1.setBmr(1800);
        c2.setBmr(1800);
        c1.setAvg(76.5);
        c2.setAvg(76.5);

        final var result = c1.equals(c2);

        Assert.assertTrue(result);
    }

    @Test
    public void TestNotEquals(){
        Counter c1 = new Counter();
        Counter c2 = new Counter();

        c1.setBmi(23.5);
        c2.setBmi(28.5);
        c1.setBmr(1800);
        c2.setBmr(1500);
        c1.setAvg(76.5);
        c2.setAvg(77.5);

        final var result = c1.equals(c2);

        Assert.assertFalse(result);
    }

    @Test
    public void TestHashEquals(){
        Counter c1 = new Counter();
        Counter c2 = new Counter();

        c1.setBmi(23.5);
        c2.setBmi(23.5);
        c1.setBmr(1800);
        c2.setBmr(1800);
        c1.setAvg(76.5);
        c2.setAvg(76.5);

        Assert.assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    public void TestHashNotEquals(){
        Counter c1 = new Counter();
        Counter c2 = new Counter();

        c1.setBmi(23.5);
        c2.setBmi(25.5);
        c1.setBmr(1800);
        c2.setBmr(1700);
        c1.setAvg(76.5);
        c2.setAvg(77.5);

        Assert.assertFalse(c1.hashCode() == c2.hashCode());
    }
}
