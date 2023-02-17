package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SmartEnemy extends GameObject {

    Handler handler;
    private GameObject player;
    private BufferedImage senemy_image;
    private  int Nhits = 0;


    public SmartEnemy(float x, float y, ID id , Handler handler) {
        super(x, y, id);
        this.handler = handler;
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                player = handler.objects.get(i);
            }
        }
        senemy_image = Game.SENEMYIMAGE;

    }
    //the collision boundries for the enemy are here
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,32,19);
    }

    @Override
    public void tick() {
        y += velY;
        x += velX;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));

        velX =  (float) ((-1.0/distance) * diffX);
        velY =  (float) ((-1.0/distance) * diffY);

        if(y <= 0 || y > Game.HEIGHT - 32){
            //here I am thinking of removing the enemy once it leaves the screen
            velY *= (float) -1;
        }
        if(x <= 0 || x > Game.HEIGHT - 32){
            //here I am thinking of removing the enemy once it leaves the screen
            velX *= (float) -1;
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
                    if (Nhits >= 30) {
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
        g.drawImage(senemy_image, (int)x, (int)y, null);
       
    }
    
}
