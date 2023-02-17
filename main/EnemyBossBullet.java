package main;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.*;

import java.awt.image.BufferedImage;

public class EnemyBossBullet extends GameObject {

    Handler handler;
    private BufferedImage bullet_image;

    Random r = new Random();
    public EnemyBossBullet(float x, float y, ID id , Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = (r.nextInt(5 - -5) + -5);
        velY = (float)-5;
        bullet_image = Game.BULLETPOKE;
    }
    //the collision boundries for the enemy are here
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (y >= Game.HEIGHT) {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.RED);
        //g.fillRect((int)x, (int)y, 16, 16);
        g.drawImage(bullet_image, (int)x, (int)y, null);

    }
    
}
