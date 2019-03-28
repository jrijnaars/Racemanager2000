package racemanager2000.Game.model;

import org.junit.Assert;
import org.junit.Test;

public class CarTest {

    @Test
    public void setName() {
        Car bmw = new Car();
        bmw.setName("1 serie");
        Assert.assertEquals("1 serie", bmw.getName());
    }
}