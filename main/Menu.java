package main;

import java.awt.*;
import java.util.Random;

import javax.swing.event.MouseInputAdapter;
import javax.swing.event.*;
import java.awt.event.MouseListener;
import java.lang.Thread.State;

import org.w3c.dom.events.MouseEvent;


import main.Game.STATE;



public class Menu implements MouseListener{

    private Game game;
    private HUD hud;
    private Handler handler;
    Random r = new Random();
    static boolean music_init;

    public  Menu(Game game , Handler handler , HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }
    
    

    private boolean mouseOver(int mx,int my,int x,int y,int width,int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public void tick(){
        if(music_init){
        game.playMusic(0);
        music_init = false;
        }
    }


    public void render(Graphics g){
        
        
        //g.drawImage(Game.BACKGROUND, (int)Game.WIDTH, (int)Game.HEIGHT, null);
        if(game.gameState == STATE.Menu){

            Font fnt = new Font("BOLD",1,50);
            Font fnt2 = new Font("BOLD",1,30);
    
            g.setFont(fnt);
            g.setColor(Color.BLACK);
            g.drawString("DA CHICKEN", 155, 70);
    
    
            
            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 280, 180);
    
            
            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 280, 280);
    
            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 380);
        }else if(game.gameState == STATE.Help){
            Font fnt3 = new Font("arial", 1, 15);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt = new Font("arial", 1, 50);
            Font fnt4 = new Font("arial", 1, 10);

            //Help
            g.setFont(fnt);
            g.setColor(Color.BLACK);
            g.drawString("Help", 250, 70);
            //TODO: CHANGE THE INFO WRITTEN HERE AND MAKE IT NICER OR SOMETHING
            g.setFont(fnt3);
            g.drawString("W = Up, A = Left, S = Down, D = Right, J= Drop Egg, ESC = pause", 90, 110);
            g.drawString("In this game your goal is to dodge enemies or shoot them down and", 90, 130);
            g.drawString("get as many points as possible.", 90, 150);
            g.setFont(fnt2);
            g.setColor(Color.blue);
            g.drawString("ENJOY!", 250, 190);
            g.drawImage(game.BENEMYIMAGE, 90, 200, null);
            g.setColor(Color.black);
            g.setFont(fnt4);
            g.drawString("Basic Cat: Slow but needs 2 eggs to be taken down.", 122, 220);
            g.drawImage(game.FENEMYIMAGE, 90, 240, null);
            g.drawString("Fast Cat: Extremly fast but needs 1 egg to be taken down.", 122, 260);
            g.drawImage(game.BOSSENEMYIMAGE, 70, 270, null);
            g.drawString("Boss Cat: The strongest cat to ever exist,it can throw balls at you so be carefull !", 122, 310);
            



            
            g.setColor(Color.BLACK);
            g.setFont(fnt2);
            g.drawString("Back", 270, 390);
            g.drawRect(210, 350, 200, 64);
        }else if(game.gameState == STATE.END){
            Font fnt = new Font("arial", 1, 50);
            g.setFont(fnt);
            g.setColor(Color.BLACK);
            g.drawString("Game Over", 185, 70);

            Font fnt3 = new Font("arial", 1, 15);
            g.setFont(fnt3);
            g.drawString("You Lost with a score of: "+ hud.getScore(), 210, 200);

            Font fnt2 = new Font("arial", 1, 30);
            g.setColor(Color.BLACK);
            g.setFont(fnt2);
            g.drawString("Try Again", 240, 390);
            g.drawRect(210, 350, 200, 64);
            
            
        }else if(game.gameState == STATE.Select){
            Font fnt = new Font("BOLD",1,50);
            Font fnt2 = new Font("BOLD",1,30);
    
            g.setFont(fnt);
            g.setColor(Color.BLACK);
            g.drawString("SELECT DIFFICULTY", 85, 70);
            
    
            
            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("EASY", 270, 180);
    
            
            g.drawRect(210, 250, 200, 64);
            g.drawString("NORMAL", 245, 280);
    
            g.drawRect(210, 350, 200, 64);
            g.drawString("HARD", 270, 380);

            g.drawRect(10, 370, 100, 64);
            g.drawString("BACK", 15, 410);
        }else if(game.gameState == STATE.Win){
            Font fnt = new Font("arial", 1, 50);
            g.setFont(fnt);
            g.setColor(Color.BLACK);
            g.drawString("Game Over", 185, 70);

            Font fnt3 = new Font("arial", 1, 15);
            g.setFont(fnt3);
            g.drawString("You Won with a score of: "+ hud.getScore(), 200, 200);

            Font fnt2 = new Font("arial", 1, 30);
            g.setColor(Color.BLACK);
            g.setFont(fnt2);
            g.drawString("Play Again?", 230, 390);
            g.drawRect(215, 350, 200, 64);

        }
                  
        
    }


    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        
        if (Game.gameState == STATE.Menu) {
            
            //Play button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                //game.stopMusic();
                //game.stopMusic();
                //
                //game.playMusic(1);
                // game.gameState = STATE.Game;
                //handler.addObject(new Player((float)Game.WIDTH/2 - 32, (float)Game.HEIGHT/2 - 32,ID.Player , handler));
                //handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,handler));
                game.stopMusic(0);
                game.playSE(3);
                game.gameState = STATE.Select;


            }

            //Quit button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.playSE(3);
                System.exit(1);
            }
            //help button
            if(mouseOver(mx, my, 210, 250, 200, 64)){
                game.stopMusic(0);
                game.playSE(3);
                game.gameState =  STATE.Help;
            }
        }
        //buttons inside help
        else if(game.gameState ==  STATE.Help){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.playSE(3);
                game.playMusic(0);
                game.gameState =  STATE.Menu;
            }
        //try again button
        }else if(game.gameState ==  STATE.END){
            if(mouseOver(mx, my, 200, 350, 200, 64)){
                Game.stopMusic(1);
                game.playSE(3);
                game.playMusic(0);
                game.gameState =  STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
                KeyInput.first_T = true;
                Spawn.scoreTillNextlvl = 800;
            }
            //PLAY AGAIN BUTTON
        }else if(game.gameState == STATE.Win){
            if(mouseOver(mx, my, 200, 350, 200, 64)){
                Game.stopMusic(7);
                game.playSE(3);
                game.playMusic(0);
                game.gameState =  STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
                KeyInput.first_T = true;
                Spawn.scoreTillNextlvl = 800;
            }

        }else if (game.gameState == STATE.Select) {
            
            //Easy button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                game.stopMusic(0);
                game.playSE(2);
                game.playMusic(1);
                game.hud.HEALTH = 100;
                game.hud.Health_copy = 100;
                game.diff = 0;
                game.transtion = true;
                game.gameState = STATE.Game;
                
            }

            //Normal button
            if(mouseOver(mx, my, 210, 250, 200, 64)){
                game.stopMusic(0);
                game.playSE(2);
                game.playMusic(1);
                game.hud.HEALTH = 80;
                game.hud.Health_copy = 80;
                game.diff = 1;
                game.transtion = true;
                game.gameState = STATE.Game;

                
            }
            //Hard button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.stopMusic(0);
                game.playSE(2);
                game.playMusic(1);
                game.hud.HEALTH = 50;
                game.hud.Health_copy = 50;
                game.diff = 2;
                game.transtion = true;
                game.gameState = STATE.Game;

            }
            //g.drawRect(10, 370, 100, 64);
            //g.drawString("BACK", 15, 410);
            //Back Button
            if(mouseOver(mx, my, 10, 370, 100, 64)){
                game.playSE(3);
                game.playMusic(0);
                game.gameState =  STATE.Menu;
            };
        }

    }
        
    

    
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    /*
    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }  */ 
}
