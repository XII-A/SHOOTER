package main;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.*;
import java.awt.image.BufferedImage;


public class PlayerEgg extends GameObject {

    //TODO:Make it so it shoots diffrent waves of bullets
    Handler handler;
    Random r = new Random();
    private BufferedImage egg_Image;
    public PlayerEgg(float x, float y, ID id , Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velY = (float)5;
        egg_Image = Game.EGGIMAGE;
    }
    //the collision boundries for the enemy are here
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }

    @Override
    public void tick() {
        y += velY;
        if (y >= Game.HEIGHT || y <= -2 ) {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(egg_Image, (int)x, (int)y, null);   
    }
    
}
