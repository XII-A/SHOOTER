package main;
import java.awt.*;

//heads up display class
public class HUD {
    public static int HEALTH = 100;
    public static int Health_copy = HEALTH;
    private int greenValue = 255;

    private int score = 0;
    private int level = 1;

    //TODO: Might change it from a health bar to a 3 soul system
    public void tick(){
    
        HEALTH = (int) Game.clamp((float)HEALTH, (float)0, (float)100);

        greenValue = (int) Game.clamp((float)greenValue, (float)0, (float)255);

        greenValue = HEALTH * 2;
        
        //score++;

    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 15, Health_copy*2, 32);
        g.setColor(new Color(75,greenValue,0));
        g.fillRect(15, 15, HEALTH*2, 32);
        g.setColor(Color.WHITE);
        g.drawRect(15, 15, Health_copy*2, 32);

        g.drawString("Score: " + score, 560, 15);
        g.drawString("Level: " + level, 560, 30);

    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }


}
