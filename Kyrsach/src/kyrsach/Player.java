package kyrsach;

import java.awt.event.*;

public class Player {
    
      private int xPos;  
      private int yPos;
      private static double gravity = 1.7;
    
    public Player(int x, int y){
        xPos = x;
        yPos = y;
    }
      
    public int xPosit(){
        return xPos;
    }
 
    public int movePlayer(){
            yPos += gravity;
            return yPos; 
    }

    public int keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE){
            yPos -= gravity + 20;
        } else {
            yPos += gravity;
        }
        return yPos;
        }
}
