package com.ing;

import javafx.scene.Node;

public class GameObject {
    protected int xpos;
    protected int ypos;
    protected Map map;
    protected Node id;


    public GameObject(Map map, Node id) {
        if (xpos<0 || ypos<0) {
            throw new IllegalArgumentException();
        }
        this.map=map;
        this.id=id;
    }

    public int getPositionx(){
        return xpos;
    }

    public int getPositiony(){
        return ypos;
    }

    public void setPositionx(int x) {
        if (x<0) {
            throw new IllegalArgumentException();
        }
        xpos=x;
    }
    public void setPositiony(int y) {
        if (y<0) {
            throw new IllegalArgumentException();
        }
        ypos=y;
    }
    public Node getId() {
        return id;
    }

}
