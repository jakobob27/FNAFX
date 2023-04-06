import org.junit.jupiter.api.Test;

import com.ing.Animatronic;
import com.ing.Map;
import com.ing.Player;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;

public class test {
    Map map;
    Player player;
    Animatronic animatronic;

    @BeforeEach
    public void setup() {
        map = new Map(10, 10);
        player = new Player(map, 8, 8, null);
        animatronic = new Animatronic(map, player, null);
        map.addPlayer(player);
        map.addAnimatronic(animatronic);
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

        assertNull(map.getGameObject(xprev, yprev));
        assertEquals(map.getGameObject(animatronic.getPositionx(), animatronic.getPositiony()), animatronic);
    }

    @Test
    public void testDead() {
        animatronic.move();
        animatronic.move();
        assertTrue(player.getDead());
    }

}
