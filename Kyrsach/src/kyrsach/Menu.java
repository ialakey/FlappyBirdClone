package kyrsach;

import java.awt.*;
import javax.swing.*;

public class Menu {

        JFrame frame = new JFrame("FlappyBird");
        public static int WIDTH = 327;
        public static int HEIGHT = 620;
    
    public static void main(String[] args) {
        Menu menu = new Menu(WIDTH, HEIGHT, "Flappy Bird");
}
    
    public Menu(int width, int height, String title){
        frame.add(new Game(frame));
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximumSize(new Dimension(width, height));
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true); 
        frame.setFocusable(true);
    }
}
