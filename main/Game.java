package main;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.lang.Thread.State;
import java.nio.Buffer;
import java.util.Random;
import java.awt.image.BufferedImage;
//import org.newdawn.slick.Music;


public class Game extends Canvas implements Runnable{
    
    public static final int WIDTH = 640 , HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    Random r;
    private Handler handler;
    public static HUD hud;
    public static Spawn spawner;
    private Menu menu;

    static Sound sound = new Sound();
    boolean  prev_song = true;

    public static boolean paused = false;
    public static boolean transtion = false;
    public static int diff = 0;
    //0=Easy
    //1=Normal
    //2=Hard
    
    
    //to define the states of the game 
    public enum STATE{
        Menu,
        Help,
        Select,
        END,
        Win,
        Game;
    }

    public static STATE gameState = STATE.Menu;

    public static BufferedImage PLAYERIMAGE;
    public static BufferedImage EGGIMAGE;
    public static BufferedImage BENEMYIMAGE;
    public static BufferedImage FENEMYIMAGE;
    public static BufferedImage SENEMYIMAGE;
    public static BufferedImage BOSSENEMYIMAGE;
    public static BufferedImage DAMGEDBOSSENEMYIMAGE;
    public static BufferedImage BULLETPOKE;
    public static BufferedImage BACKGROUND;
    public static BufferedImage EGGSTATEIMAGE;






    public Game(){
        handler = new Handler();
        
        hud = new HUD();

        menu = new Menu(this,handler,hud);

        this.addKeyListener(new KeyInput(handler,this));
        
        this.addMouseListener(menu);

        Menu.music_init = true;

        //AudioPlayer.load();

        new Window(WIDTH, HEIGHT, "Chicken Game", this);
        
        BufferedImageLoader loader = new BufferedImageLoader();

        PLAYERIMAGE = loader.loadImage("/res/TheChicken.png");
        EGGSTATEIMAGE = loader.loadImage("/res/Eggchicken2.png");
        BENEMYIMAGE = loader.loadImage("/res/CAT32.png");
        FENEMYIMAGE = loader.loadImage("/res/rainbowcat.png");
        SENEMYIMAGE = loader.loadImage("/res/SMARTO.png");
        BOSSENEMYIMAGE = loader.loadImage("/res/BigBoss.png");
        EGGIMAGE = loader.loadImage("/res/Egg1.png");
        DAMGEDBOSSENEMYIMAGE = loader.loadImage("/res/BigBossDamged.png");
        BULLETPOKE = loader.loadImage("/res/pokeball.png");
        BACKGROUND = loader.loadImage("/res/GreenBackground.png");


        spawner = new Spawn(handler,hud,this);

        
        r = new Random();
        
        if (gameState == STATE.Game) {
            
            handler.addObject(new Player((float)WIDTH/2 - 32, (float)HEIGHT/2 - 32,ID.Player , handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,handler));
        }
        
        
        
        
        
    }



    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop(){
       try {
           thread.join();
           running = false;
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
    //gmae loop
    @Override
    public void run() {
       this.requestFocus();
       long lastTime = System.nanoTime();
       double amountOfTicks = 60.0;
       double ns = 1000000000 / amountOfTicks;
       double delta = 0;
       long timer = System.currentTimeMillis();
       int frames = 0;
       while (running) {
           long now = System.nanoTime();
           delta += (now - lastTime) / ns;
           lastTime = now;
           while(delta >= 1 ){
               tick();
               delta--;
           }
           if (running) {
               render();
           }
           frames++;
           if (System.currentTimeMillis() - timer > 1000) {
               timer += 1000;
               //System.out.println("FPS: " + frames);
               frames = 0;
           }

       }
    stop();
    }
    private void tick(){
       
        if (gameState == STATE.Game) {
            if(!paused && !transtion){
                
                hud.tick();
                spawner.tick();
                handler.tick();

                if(HUD.HEALTH <= 0 ){
                    HUD.HEALTH = 100;
                    gameState = STATE.END;
                    Game.stopMusic(1);
                    handler.clearEnemys();
                }

            }
        }else if(gameState == STATE.Menu || gameState == STATE.END || gameState == STATE.Select ||  gameState == STATE.Win){
            menu.tick();
            handler.tick();

        }
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(BACKGROUND, 0, 0, null);
        
        
        handler.render(g);

        if(paused){
            g.setColor(Color.BLACK);
            g.drawString("PAUSED", 100, 100);
        }
        if(transtion){
            g.setColor(Color.BLACK);
            g.drawString("Level: "+ hud.getLevel(), WIDTH/2 - 50, 100);
            //Maybe add flicker here
            g.drawString("Press Space To Begin", WIDTH/2 - 90 , 150);
        }

        if (gameState == STATE.Game) {
            hud.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.END || gameState == STATE.Select || gameState == STATE.Win){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(Float var,Float min,Float max){
        if(var >= max){
            return var = max;
        }
        else if(var <= min){
            return var = min;
        }
        else
            return var;
    }

    public static void playMusic(int i){
        sound.setFile(i);
        sound.play(i);
        sound.loop(i);
    }
    public static void stopMusic(int i){
        sound.stop(i);
    }
    public static void playSE(int i){
        sound.setFile(i);
        sound.play(i);
    }




    public static void main(String[] args) {
        new Game();
    }

}