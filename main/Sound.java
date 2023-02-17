package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip[] = new Clip[30];
    URL  soundURL[] = new URL[30];
    


    public Sound(){
        soundURL[0] = getClass().getResource("/res/Menu_Song.wav");
        soundURL[1] = getClass().getResource("/res/GameMusic.wav");
        soundURL[2] = getClass().getResource("/res/Start_button.wav");
        soundURL[3] = getClass().getResource("/res/Menu_click.wav");
        soundURL[4] = getClass().getResource("/res/Egg_drop.wav");
        soundURL[5] = getClass().getResource("/res/CatAttack.wav");
        soundURL[6] = getClass().getResource("/res/CatDamage.wav");
        soundURL[7] = getClass().getResource("/res/wavWin.wav");


    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip[i] = AudioSystem.getClip();
            clip[i].open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(int i){
        clip[i].start();
    }
    public void loop(int i){
        clip[i].loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(int i){
        BooleanControl muteControl = (BooleanControl) clip[i].getControl(BooleanControl.Type.MUTE);
        if(muteControl != null){
            muteControl.setValue(true); // True to mute the line, false to unmute
        }

        clip[i].loop(0); // Stops the sound after it runs through its buffer
        clip[i].flush();
    }
}
