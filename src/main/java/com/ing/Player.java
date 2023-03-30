package com.ing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;

public class Player extends GameObject implements MapListener {

    private boolean dead = false;
    private List<JumpscareListener> listeners = new ArrayList<>();

    public Player(Map map, int xpos, int ypos, Node id) {
        super(map, id);
        this.xpos = xpos;
        this.ypos = ypos;
    }

    @Override
    public void update(GameObject o) {
        for (Animatronic animatronic : map.getAnimatronics()) {
            if (animatronic.getPositionx() == xpos && animatronic.getPositiony() == ypos) {
                dead = true;
                System.out.println("YOU ARE DEAD!");
                for (Animatronic anima : map.getAnimatronics()) {
                    anima.stopMoving();
                }

                for (JumpscareListener l : listeners) {
                    l.jumpscare(animatronic);
                }

                break;
            }
        }
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean getDead() {
        return dead;
    }

    public void addJumpscareListener(JumpscareListener listener) {
        listeners.add(listener);
    }

}
