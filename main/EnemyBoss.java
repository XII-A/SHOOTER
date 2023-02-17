package main;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import main.Game.STATE;

import java.awt.*;
import java.awt.image.BufferedImage;


public class EnemyBoss extends GameObject {

    private BufferedImage bossenemy_image;
    
    Handler handler;
    public static int boss_killed = 0;
    private int timer = 100;
    private int timer2 = 50;
    private  int Nhits = 0;
    Random r = new Random();
    //TODO: Give the boss health and remove it once its killed same thing with other enemies
    public EnemyBoss(float x, float y, ID id , Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = (float)0;
        velY = (float)-2;
        bossenemy_image = Game.BOSSENEMYIMAGE;

    }
    //the collision boundries for the enemy are here
    //+280 for height and -48 for width was perfect and speed is -2
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,64,64);
    }

    @Override
    public void tick() {
        y += velY;
        x += velX;


        if (timer <= 0) {
            velY = 0;
        }else{
            timer--;
        }

        if (timer <= 0) {
            timer2--;
        }

        if (timer2 <= 0) {
            if (velX == 0){
                velX = (float)2;
            }
            int spawn = r.nextInt(10);
            if(spawn == 0){
                handler.addObject(new EnemyBossBullet((int)x + 32, (int)y, ID.BasicEnemy, handler));
            }
        }
        
        if(x <= 0 || x > Game.WIDTH - 80){
            
            velX *= -1;
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
                    bossenemy_image = Game.DAMGEDBOSSENEMYIMAGE;
                    if (Nhits >= 120 && (Game.diff == 0 || Game.diff == 1)) {
                        handler.removeObject(this);
                        handler.objects.clear();
                        Game.stopMusic(1);
                        Game.playSE(6);
                        Game.playMusic(7);
                        Game.hud.setScore(Game.hud.getScore()+10000);
                        Game.spawner.setScoreKeep(Game.spawner.getScoreKeep() + 10000);
                        Game.gameState = STATE.Win;
                    }else if(Nhits >= 120 &&  Game.diff == 2 && boss_killed == 0){
                        handler.removeObject(this);
                        Game.hud.setScore(Game.hud.getScore()+10000);
                        Game.spawner.setScoreKeep(Game.spawner.getScoreKeep() + 10000);
                        boss_killed++;
                    }else if(Nhits >= 120 &&  Game.diff == 2 && boss_killed == 1){
                        handler.removeObject(this);
                        boss_killed++;
                    }
                    else if(Nhits >= 120 &&  Game.diff == 2 && boss_killed == 2){
                        handler.removeObject(this);
                        handler.objects.clear();
                        boss_killed = 0;
                        Game.stopMusic(1);
                        Game.playSE(6);
                        Game.playMusic(7);
                        Game.hud.setScore(Game.hud.getScore()+10000);
                        Game.spawner.setScoreKeep(Game.spawner.getScoreKeep() + 10000);
                        Game.gameState = STATE.Win;
                    }
                    
                    
                }else{
                    bossenemy_image = Game.BOSSENEMYIMAGE;

                }
            }

        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bossenemy_image, (int)x, (int)y, null);
        
    }
}
    