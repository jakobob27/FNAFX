package com.ing;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.scene.Node;


public class Animatronic extends GameObject{
    private Random random = new Random();
    private long aggro = 2001;
    private final long minimumAggro=200;
    private Player player;
    private ScheduledExecutorService scheduler;
    

    public Animatronic(Map map, Player player, Node id) {
        super(map, id);
        this.player=player;
    }

    // Advarsel! Masse uoptimisert tullball innkommende!

    public void move() {
        aggro*=0.95;
        
        if (aggro<minimumAggro) {
            this.stopMoving();
            aggro=2000;
            map.updateListeners(this);
            return;
            
        }

        if (xpos>player.getPositionx() && ypos>player.getPositiony()) {
            if(random.nextBoolean() && map.checkPosition(xpos-1, ypos) && map.animatronicCollision(xpos-1, ypos)) {
                map.moveGameObject(xpos, ypos, xpos-1, ypos);
                
            }
            else if (map.checkPosition(xpos, ypos-1) && map.animatronicCollision(xpos, ypos-1)){
                map.moveGameObject(xpos, ypos, xpos, ypos-1);
            
            }
        }

        else if (xpos<player.getPositionx() && ypos > player.getPositiony()) {
            if(random.nextBoolean() && map.checkPosition(xpos+1, ypos) && map.animatronicCollision(xpos+1, ypos)){
                map.moveGameObject(xpos, ypos, xpos+1, ypos);
                
            }
            else if (map.checkPosition(xpos, ypos-1) && map.animatronicCollision(xpos, ypos-1)) {
                map.moveGameObject(xpos, ypos, xpos, ypos-1);
                
            }
        }

        else if (xpos<player.getPositionx() && ypos < player.getPositiony()) {
            if(random.nextBoolean() && map.checkPosition(xpos+1, ypos) && map.animatronicCollision(xpos+1, ypos)){
                map.moveGameObject(xpos, ypos, xpos+1, ypos);
                
            }
            else if (map.checkPosition(xpos, ypos+1) && map.animatronicCollision(xpos, ypos+1)) {
                map.moveGameObject(xpos, ypos, xpos, ypos+1);
                
            }
        }

        else if (xpos>player.getPositionx() && ypos < player.getPositiony()) {
            if(random.nextBoolean() && map.checkPosition(xpos-1, ypos) && map.animatronicCollision(xpos-1, ypos)){
                map.moveGameObject(xpos, ypos, xpos-1, ypos);
                
            }
            else if (map.checkPosition(xpos, ypos-1) && map.animatronicCollision(xpos, ypos-1)) {
                map.moveGameObject(xpos, ypos, xpos, ypos+1);
                
            }
        }

        else if (xpos==player.getPositionx() && ypos>player.getPositiony() && map.checkPosition(xpos, ypos-1) && map.animatronicCollision(xpos, ypos-1)) {
            map.moveGameObject(xpos, ypos, xpos, ypos-1);
            
        }
        else if (xpos==player.getPositionx() && ypos<player.getPositiony() && map.checkPosition(xpos, ypos+1) && map.animatronicCollision(xpos, ypos+1)) {
            map.moveGameObject(xpos, ypos, xpos, ypos+1);
            
        }

        else if (ypos== player.getPositiony() && xpos>player.getPositionx() && map.checkPosition(xpos-1, ypos) && map.animatronicCollision(xpos-1, ypos)) {
            map.moveGameObject(xpos, ypos, xpos-1, ypos);
            
        }

        else if (ypos == player.getPositiony() && xpos<player.getPositionx() && map.checkPosition(xpos+1, ypos) && map.animatronicCollision(xpos+1, ypos)) {
            map.moveGameObject(xpos, ypos, xpos+1, ypos);
            
        }
        
    }

    public void startMoving() {
        if (scheduler != null) {
            scheduler.shutdownNow();
        }
    
        scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable moveTask = new Runnable() {
            @Override
            public void run() {
                move();
            }
        };
    
        scheduler.scheduleAtFixedRate(moveTask, 2000, aggro, TimeUnit.MILLISECONDS);
    
        Runnable rescheduleTask = new Runnable() {
            @Override
            public void run() {
                scheduler.shutdown();
                startMoving();
            }
        };
    
        scheduler.schedule(rescheduleTask, aggro * 10, TimeUnit.MILLISECONDS);
    }

    public void stopMoving() {
        scheduler.shutdownNow();
    }

    public long getAggro() {
        return aggro;
    }
    
    public void setAggro (long aggro) {
        this.aggro=aggro;
    }

}
