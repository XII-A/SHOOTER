package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends GameObject {

    Handler handler;
    boolean sound_init = true;
    public static BufferedImage player_image;
    public Player(Float x,Float y , ID id , Handler handler){
        super(x, y, id);
        this.handler = handler;
        //SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        player_image = Game.PLAYERIMAGE;

    }
    //we are sitting the collison boundries for the player here
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,32,32);
    }
    @Override
    public void tick() {
        x += velX;
        y += velY;
        x = Game.clamp((Float)x, (float)0, (float)Game.WIDTH - 45);
        y = Game.clamp((Float)y, (float)0, (float)Game.HEIGHT - 65);

        collision();
    }
    private void collision(){
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //COLLISION CODE 
                    HUD.HEALTH -=2;
                    
                }
            }
            if (tempObject.getId() == ID.EnemyBoss) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //COLLISION CODE 
                    HUD.HEALTH -= 100;
                    
                }
            }

        }
    }

    @Override
    public void render(Graphics g) {
        

        //g.setColor(Color.white);
        //g.fillRect((int)x, (int)y, 32, 32);

        g.drawImage(player_image, (int)x, (int)y, null);
        
    }


    
}
