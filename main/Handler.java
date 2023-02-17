package main;

import java.util.LinkedList;
import java.awt.*;

public class Handler {
    LinkedList<GameObject> objects = new LinkedList<GameObject>();

    public void tick(){
        //this will loop through our objects
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object){
        this.objects.add(object);
    }
    public void removeObject(GameObject object){
        this.objects.remove(object);
    }
    public void clearEnemys(){
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            if (tempObject.getId() == ID.Player) {
                objects.clear();
                if (Game.gameState != Game.STATE.END) {
                    addObject(new Player( (float)tempObject.getX(),(float) tempObject.getY(), ID.Player, this));
                }
            }
        }
    }
    
}
