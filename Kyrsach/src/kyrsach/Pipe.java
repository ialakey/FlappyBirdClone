package kyrsach;

import java.util.Random;

public class Pipe {
    
    private static int pipeSpeed = 2;
    private int x = 310;
    public int posY1;
    public int posY2;
    
    private int score = 0; 
    
    public Pipe(int y1, int y2){
        posY1 = y1;
        posY2 = y2;
    }
    
    public int pipeMove(){
        x -=pipeSpeed;
        return x;
    }
  
    public int myPoints(){
        if (x < -59){
            score += 1;
        }
        return score;
    } 
    
    public void randomPosition(){
        if (x < -60){
            Random vart = new Random();
            int variant = vart.nextInt(4);
            x = 310;
            switch (variant) {
                case 0:
                    posY1 = 340;
                    posY2 = 0;
                    break;
                case 1:
                    posY1 = 280;
                    posY2 = -60;
                    break;
                case 2:
                    posY1 = 200;
                    posY2 = -140;
                    break; 
                case 3:
                    posY1 = 160;
                    posY2 = -180;
                    break;
                default:
                    break;
            }
              
        }
    } 
}
