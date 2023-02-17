package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.image.BufferedImage;


public class FastEnemy extends GameObject { 
    Handler handler;
    private BufferedImage fenemy_image;
    private  int Nhits = 0;


    public FastEnemy(float x, float y, ID id , Handler handler){
        super(x, y, id);
        this.handler = handler;
        velY = (float)-4;
        fenemy_image = Game.FENEMYIMAGE;
    }



    //the collision boundries for the enemy are here
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,32,32);
    }

    @Override
    public void tick() {
        y += velY;
        if(y <= 0 || y > Game.HEIGHT - 32){
            //here I am thinking of removing the enemy once it leaves the screen
            handler.removeObject(this);
            Game.hud.setScore(Game.hud.getScore()+75);
            Game.spawner.setScoreKeep(Game.spawner.getScoreKeep() + 75);
        }   
        collision();    
    }
    private void collision(){
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.Bullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //COLLISION CODE 
                    Nhits++;
                    if (Nhits >= 2) {
                        handler.removeObject(this);
                        Game.playSE(6);
                        Game.hud.setScore(Game.hud.getScore()+125);
                        Game.spawner.setScoreKeep(Game.spawner.getScoreKeep() + 125);
                        
                    }
                    
                    
                }
            }

        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(fenemy_image, (int)x, (int)y, null);
    }

   
}
