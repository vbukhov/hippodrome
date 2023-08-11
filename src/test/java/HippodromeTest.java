import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void emptyHorsesException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));

    }
    @Test
    public void emptyListHorses(){
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e){
            assertEquals("Horses cannot be empty.",e.getMessage());
        }
    }
    @Test
    public void nullHorsesException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

    }
    @Test
    public void nullListHorses(){
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e){
            assertEquals("Horses cannot be null.",e.getMessage());
        }
    }
    @Test
    public void getHorses(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("horse"+i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }
    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i <50 ; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();

        for (Horse horse : horses){
            verify(horse).move();
        }
    }
    @Test
    public void getWinner(){
        Horse horse1 = new Horse("horse1", 1, 3.0);
        Horse horse2 = new Horse("horse2", 1, 2.0);
        Horse horse3 = new Horse("horse3", 1, 4.0);
        Horse horse4 = new Horse("horse4", 1, 1.0);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4));

        assertSame(horse3, hippodrome.getWinner());
    }
}
