package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject {

    
    Handler handler;
    private BufferedImage enemy_image;
    private  int Nhits = 0;
    private int timer = 0;

    public BasicEnemy(float x, float y, ID id , Handler handler) {
        super(x, y, id);
        this.handler = handler;
        //velX = (float)5;
        velY = (float)-5;
        enemy_image = Game.BENEMYIMAGE;
    }
    //the collision boundries for the enemy are here
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,32,32);
    }

    @Override
    public void tick() {
        timer++;
        if(timer >= 15){
            y += velY;
            timer = 0;
        }
        if(y <= 0 || y > Game.HEIGHT - 32){
            //here I am thinking of removing the enemy once it leaves the screen
            handler.removeObject(this);
            Game.hud.setScore(Game.hud.getScore()+50);
            Game.spawner.setScoreKeep(Game.spawner.getScoreKeep() + 50);
            
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
                    if (Nhits >= 25) {
                        handler.removeObject(this);
                        Game.playSE(6);
                        Game.hud.setScore(Game.hud.getScore()+100);
                        Game.spawner.setScoreKeep(Game.spawner.getScoreKeep() + 100);
                        
                    }
                    
                    
                }
            }

        }
    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.RED);
        //g.fillRect((int)x, (int)y, 16, 16);
        g.drawImage(enemy_image, (int)x, (int)y, null);
    }
    
}
