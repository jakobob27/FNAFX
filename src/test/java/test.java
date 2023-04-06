import org.junit.jupiter.api.Test;

import com.ing.Animatronic;
import com.ing.GameObject;
import com.ing.Map;
import com.ing.MapListener;
import com.ing.Player;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;

public class test implements MapListener {
    Map map;
    Player player;
    Animatronic animatronic;
    boolean checker = false;

    @Override
    public void update(GameObject o) {
        checker = true;
    }

    @BeforeEach
    public void setup() {
        map = new Map(10, 10);
        player = new Player(map, 8, 8, null);
        animatronic = new Animatronic(map, player, null);
        map.addPlayer(player);
        map.addAnimatronic(animatronic);
        map.addListener(this);
        checker = false;
    }

    @Test
    public void testMapSize() {
        assertEquals(map.getMapSizeX(), 10);
        assertEquals(map.getMapSizeY(), 10);
    }

    @Test
    public void testAnimatronicMove() {
        int xprev = animatronic.getPositionx();
        int yprev = animatronic.getPositiony();
        animatronic.move();

        assertTrue(animatronic.getPositionx() == 8 && animatronic.getPositiony() == 9
                || animatronic.getPositionx() == 9 && animatronic.getPositiony() == 8);
        assertNull(map.getGameObject(xprev, yprev));
        assertEquals(map.getGameObject(animatronic.getPositionx(), animatronic.getPositiony()), animatronic);
    }

    @Test
    public void testDead() {
        animatronic.move();
        animatronic.move();
        assertTrue(player.getDead());
    }

    @Test
    public void testListener() {
        animatronic.move();
        assertTrue(checker);
    }

    @Test
    public void testException() {
        Player dumbass = new Player(map, 13, 37, null);
        assertThrows(IllegalArgumentException.class, () -> map.addPlayer(dumbass));
    }
}
