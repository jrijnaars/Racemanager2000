package racemanager2000.Game.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CarTest {

    @Test
    public void getName() {
        Car auto = new Car("test", 99,99);
        Assertions.assertThat(auto.getName()).isEqualTo("test");
    }

    @Test
    public void setName() {
        Car Derby = new Car("test", 7, 5);
        Derby.setName("Cars");
        Assertions.assertThat(Derby.getName()).isEqualTo("Cars");
    }

    @Test
    public void getChassis() {
        Car C = new Car("test", 89, 75);
        Assertions.assertThat(C.getChassis()).isEqualTo(89);
    }

    @Test
    public void setChassis() {
        Car C = new Car("test", 9, 9);
        C.setChassis(89);
        Assertions.assertThat(C.getChassis()).isEqualTo(89);
    }

    @Test
    public void getEngine() {
    }

    @Test
    public void setEngine() {
    }

    @Test
    public void setCarAbillityOverall() {
    }

    @Test (expected = IllegalArgumentException.class)
    public void testnamenotnull(){
        Car car = new Car(null, 99, 99);
    }
}