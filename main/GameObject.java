package main;
import java.awt.*;
//where all objects are gonna inherit from
public abstract class GameObject {
    protected float x , y;
    protected ID id;
    protected float velX,velY;

    public GameObject(float x,float y,ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(Float x) {
        this.x = x;
    }
    public void setY(Float y) {
        this.y = y;
    }
    public Float getX() {
        return x;
    }
    public Float getY() {
        return y;
    }

    public void setId(ID id) {
        this.id = id;
    }
    public ID getId() {
        return id;
    }

    public void setVelX(Float velX) {
        this.velX = velX;
    }
    public void setVelY(Float velY) {
        this.velY = velY;
    }
    public Float getVelX() {
        return velX;
    }
    public Float getVelY() {
        return velY;
    }
}
