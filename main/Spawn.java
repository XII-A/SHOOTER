package main;

import java.util.Random;

import main.Game.STATE;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private Game game;
    private Random r = new Random();
    private int timer = 0;

    public static int scoreKeep = 0;
    public static int scoreTillNextlvl = 800;

    public Spawn(Handler handler, HUD hud,Game game){
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void tick(){
        if (game.gameState == STATE.Game) {
            //scoreKeep++;
            timer++;
        }
   
    


        //Keeping track of the score here we increase it and go to the next levels accordingly
        if (Game.diff == 0 || Game.diff == 1){

            if(scoreKeep >= scoreTillNextlvl){
                scoreKeep = 0;
                scoreTillNextlvl += 300;
                hud.setLevel(hud.getLevel() + 1);
                handler.clearEnemys();
                if(hud.getLevel()  == 2 ){
                    for (int i = 0; i < game.WIDTH - 40; i+=32) {
                        
                        handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH - 32 ), 395, ID.BasicEnemy,handler));
                    }
                    for (int i = 0; i < 6; i++) {
                        //handler.addObject(new BasicEnemy(i, 395, ID.BasicEnemy,handler));
                        handler.addObject(new FastEnemy(r.nextInt(game.WIDTH), 405, ID.FastEnemy,handler));
                    }



                }
                else if(hud.getLevel()  == 3 ){
                    handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, Game.HEIGHT/2 + 280, ID.EnemyBoss,handler));
                    
                }/*
                else if(hud.getLevel()  == 4 ){
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 100), 380, ID.SmartEnemy,handler));
                    
                }
                else if(hud.getLevel()  == 5 ){
                    handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, Game.HEIGHT/2 + 280, ID.EnemyBoss,handler));
                    
                }*/
                if (hud.getLevel()  <= 3) {
                    game.transtion = true;
                    
                }else if(hud.getLevel()  >= 4){
                    scoreKeep = 0;
                    hud.setScore(0); 
                }
            }

        }
        else if(Game.diff == 2){

            //TODO:Make it so more enemies spawn when its hard
            if(scoreKeep >= scoreTillNextlvl){
                scoreKeep = 0;
                scoreTillNextlvl += 200;
                hud.setLevel(hud.getLevel() + 1);
                handler.clearEnemys();
                if(hud.getLevel()  == 2 ){
                    for (int i = 0; i < game.WIDTH - 40; i+=32) {
                        
                        handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH - 32 ), 395, ID.BasicEnemy,handler));
                    }
                    for (int i = 0; i < 6; i++) {
                        //handler.addObject(new BasicEnemy(i, 395, ID.BasicEnemy,handler));
                        handler.addObject(new FastEnemy(r.nextInt(game.WIDTH) , 405, ID.FastEnemy,handler));
                    }



                }
                else if(hud.getLevel()  == 3 ){
                    for (int i = 0; i < 6; i++) {
                        //handler.addObject(new BasicEnemy(i, 395, ID.BasicEnemy,handler));
                        handler.addObject(new FastEnemy(r.nextInt(game.WIDTH) , 405, ID.FastEnemy,handler));
                    }
                    EnemyBoss.boss_killed = 0;
                    handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, Game.HEIGHT/2 + 280, ID.EnemyBoss,handler));
                    
                }
                else if(hud.getLevel()  == 4 ){
                    for (int i = 0; i < 30; i++) {
                        //handler.addObject(new BasicEnemy(i, 395, ID.BasicEnemy,handler));
                        handler.addObject(new FastEnemy(r.nextInt(game.WIDTH) , 405, ID.FastEnemy,handler));
                    }
                    
                }
                else if(hud.getLevel()  == 5 ){
                    for (int i = 0; i < game.WIDTH - 40; i+=32) {
                        
                        handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH - 32 ), 395, ID.BasicEnemy,handler));
                    }
                        handler.addObject(new SmartEnemy( game.WIDTH/2 ,395, ID.SmartEnemy,handler));
                    
                }else if(hud.getLevel() == 6){
                    handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, Game.HEIGHT/2 + 280, ID.EnemyBoss,handler));
                    handler.addObject(new EnemyBoss((Game.WIDTH/2)-200, Game.HEIGHT/2 + 280, ID.EnemyBoss,handler));

                }
                if (hud.getLevel()  <= 6) {
                    game.transtion = true;
                    
                }else if(hud.getLevel()  >= 7){
                    scoreKeep = 0;
                    hud.setScore(0); 
                }
            }
        }


    }
    public int getScoreKeep(){
        return scoreKeep;
    }
    public void setScoreKeep(int value){
        scoreKeep = value;
    }

}


