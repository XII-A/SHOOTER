package main;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.image.BufferedImage;

public class Window extends Canvas{
    //public static BufferedImage BACKGROUND;

    public Window(int width,int height,String title , Game game){
        JFrame frame  = new JFrame(title);
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        
        /*frame.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("/res/GreenBackground.png"));
        frame.add(background);
        background.setLayout(new FlowLayout());*/


        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
