import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InOrder;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameException(){
        assertThrows(IllegalArgumentException.class,() -> new Horse(null, 1, 1));
    }

    @Test
    public void nullNameMassage(){
        try {
            new Horse(null, 1, 1);
        } catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.",e.getMessage());
        }

    }
    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t\t", "\n\n\n\n\n"})
    public void blankNameException(String name){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() -> new Horse(name, 1, 1));

        assertEquals("Name cannot be blank.",e.getMessage());

    }
    @ParameterizedTest
    @ValueSource(doubles = -1.0)
    public void negativeSpeedException(double speed){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() -> new Horse("Pegasus", speed, 1));

        assertEquals("Speed cannot be negative.",e.getMessage());

    }

    @ParameterizedTest
    @ValueSource(doubles = -1.0)
    public void negativeDistanceException(double distance){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() -> new Horse("Pegasus", 1, distance));

        assertEquals("Distance cannot be negative.",e.getMessage());

    }
    @Test
    public void getName(){
        Horse horse = new Horse("Veter",1,1);

        assertEquals("Veter", horse.getName());
    }
    @Test
    public void getSpeed() {
        Horse horse = new Horse("Veter", 60, 1);

        assertEquals(60, horse.getSpeed());
    }
    @Test
    public void getDistance() {
        Horse horse = new Horse("Veter", 1, 500);

        assertEquals(500, horse.getDistance());
    }
    @Test
    public void getDistanceZero() {
        Horse horse = new Horse("Veter", 1);

        assertEquals(0, horse.getDistance());
    }
    @Test
    void moveUsesGetRandom(){
        try (MockedStatic<Horse> moscedStatic = mockStatic(Horse.class)){
            new Horse("Veter", 1, 500).move();

            moscedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.1,0.2,0.5,0.9,2.0,50.5,0.0})
    void move(double random){
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("horse",20, 100);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(random);

            horse.move();

            assertEquals(100+20*random, horse.getDistance());
        }
    }
}
