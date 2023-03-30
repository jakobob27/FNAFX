package com.ing;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Map {
    private ArrayList<ArrayList<GameObject>> grid = new ArrayList<>();
    private Collection<MapListener> listeners = new ArrayList<>();
    private int maxSizex;
    private int maxSizey;
    private Player player;
    private ArrayList<Animatronic> animatronics = new ArrayList<>();

    public Map (int x, int y){
        if (x<1|| y<1) {
            throw new IllegalArgumentException();
        }
        for (int i=0; i<x; i++) {
            grid.add(new ArrayList<GameObject>());
            for (int j=0; j<y; j++) {
                grid.get(i).add(null);
            }
        }
        this.maxSizex=x;
        this.maxSizey=y;
    }

    @Override
    public String toString() {
        return "Rader: "+ grid.size() + " Kolonner: " + grid.get(0).size();
    }

    public boolean checkPosition (int x, int y) {
        if (x>=0 && y>=0 && x<maxSizex && y<maxSizey) {
            return true;
        }
        else {
            return false;
        }
    }

    public GameObject getGameObject(int x, int y) {
        return grid.get(x).get(y);
    }

    public void moveGameObject(int xprev, int yprev, int xpos, int ypos) {
        if(!checkPosition(xpos, ypos)) {
            throw new IllegalArgumentException();
        }
        GameObject gameObject = grid.get(xprev).get(yprev);
        grid.get(xpos).set(ypos, gameObject);
        grid.get(xprev).set(yprev, null);
        gameObject.setPositionx(xpos);
        gameObject.setPositiony(ypos);
        for (MapListener l : listeners) {
            l.update(gameObject);
        }
    }

    public void addAnimatronic (Animatronic animatronic) {
        for (int i = grid.size()-1; i>0; i--) {
            for (int j = grid.get(i).size()-1; j>0; j--) {
                if(grid.get(i).get(j)==null) {
                    grid.get(i).set(j, animatronic);
                    animatronic.setPositionx(i);
                    animatronic.setPositiony(j);
                    animatronics.add(animatronic);
                    return;
                }
            }
        }
        
         }

    public boolean animatronicCollision(int x, int y) {
        if (grid.get(x).get(y) instanceof Animatronic) {
            return false;
        }
        else {
            return true;
        }
    }


    public ArrayList<Animatronic> getAnimatronics() {
        return animatronics;
    }

    public void addPlayer(Player player) {
        if(!checkPosition(player.getPositionx(), player.getPositiony()) || this.player!=null) {
            throw new IllegalArgumentException();
        }
        grid.get(player.getPositionx()).set(player.getPositiony(), player);
        this.player=player;
        listeners.add(player);
        
        
    }



    public void addListener(MapListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MapListener listener) {
        listeners.remove(listener);
    }

    public void updateListeners(GameObject gameObject) {
        for (MapListener l : listeners) {
            l.update(gameObject);
        } 
    }


    public void gameOver() {
        try {
            Thread.sleep(8000);
        try {
            App.setRoot("GameOver");
        }
        catch (IOException e) {
            e.printStackTrace();
        } }
        catch (InterruptedException f) {
            f.printStackTrace();
        }
    }
}
