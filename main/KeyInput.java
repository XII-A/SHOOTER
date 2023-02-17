package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import main.Game.STATE;
public class KeyInput implements KeyListener {

    private Handler handler;
    public static boolean first_T = true;
    Game game;
    Random r = new Random();
    public KeyInput(Handler handler,Game game){
        this.handler = handler;
        this.game = game;
    }

    public boolean upPressed,downPressed,leftPressed,rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {
        //Left empty on purpose
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();


        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject =  handler.objects.get(i);
            
            if (tempObject.getId() == ID.Player) {
                //KEY EVENTS FOR PLAYER 
                if(key == KeyEvent.VK_W){
                    upPressed = true;
                    tempObject.setVelY((float) -5);

                }
        
                if(key == KeyEvent.VK_S){
                    downPressed = true;
                    tempObject.setVelY((float) 5);
                }
        
                if(key == KeyEvent.VK_A){
                    leftPressed = true;
                    tempObject.setVelX((float) -5);
                }
        
                if(key == KeyEvent.VK_D){
                    rightPressed = true;
                    tempObject.setVelX((float) 5);
                }
                if(key == KeyEvent.VK_J){
                    handler.addObject(new PlayerEgg((int)tempObject.x, (int)tempObject.y, ID.Bullet, handler));
                    Player.player_image = Game.EGGSTATEIMAGE;
                    
                }

                    
            }
            
        }
        if(key == KeyEvent.VK_ESCAPE){
            if (Game.gameState == STATE.Game ) {

                if(Game.paused){
                    Game.paused = false;
                }else{
                    Game.paused = true;
                }
                
            }
        }
        if(key == KeyEvent.VK_SPACE){
            if (game.transtion) {
                //What happenes at the first level
                game.transtion = false;
                if (first_T) {
                    Spawn.scoreKeep = 0;
                    handler.addObject(new Player((float)Game.WIDTH/2 - 32, (float)Game.HEIGHT/2 - 32,ID.Player , handler));
                    for (int i = 0; i < game.WIDTH - 40; i+=32) {
                        handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH - 32), 395, ID.BasicEnemy,handler));

                    }

                    first_T = false;
                    
                }
                
            }
        }
        
    }



    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();


        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject =  handler.objects.get(i);
            
            if (tempObject.getId() == ID.Player) {
                //KEY EVENTS FOR PLAYER 
                if(key == KeyEvent.VK_W){
                    upPressed = false;
                }
        
                if(key == KeyEvent.VK_S){
                    downPressed = false;
                }
        
                if(key == KeyEvent.VK_A){
                    leftPressed = false;
                }
        
                if(key == KeyEvent.VK_D){
                    rightPressed = false;
                }

                //vertical movement
                if(!upPressed && !downPressed){
                    tempObject.setVelY((float) 0);
                }
                //Horizontal movement
                if(!leftPressed && !rightPressed){
                    tempObject.setVelX((float) 0);
                }

                if(key == KeyEvent.VK_J){
                    Player.player_image = Game.PLAYERIMAGE;
                   
                }
            }

        }
    
    }
}
