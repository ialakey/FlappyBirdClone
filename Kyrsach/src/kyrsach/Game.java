package kyrsach;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Game extends JPanel implements ActionListener{
    
    Image bg = new ImageIcon("img/bg.png").getImage();
    Image bird = new ImageIcon("img/bird.png").getImage();
    Image fg = new ImageIcon("img/fg.png").getImage();
    Image pipeBottom = new ImageIcon("img/pipeBottom.png").getImage();
    Image pipeUp = new ImageIcon("img/pipeUp.png").getImage();
    Image plays = new ImageIcon("img/play.png").getImage();
    Image ruls = new ImageIcon("img/rules.png").getImage();
    
    JButton playr = new JButton("Играть");
    JButton rull = new JButton("Правила");
     
    JFrame frame;
    Player player = new Player(40, 170);
    Pipe pipe = new Pipe(340, 0);
    
    private boolean isRunning = false;
    private int Width = 52;
    private int Height1 = 378;
    private int Height2 = 242;
    
    private int bestScore;
    private String stringScore;

    public Game(JFrame frame){
        this.frame = frame;
        Timer timer = new Timer(40, this);
        timer.start();
        Play();
        Ruls();
        frame.addKeyListener(new KeyAdapter() {
            @Override
        public void keyPressed(KeyEvent e){
            if (isRunning){
            player.keyPressed(e);
            }
        }
        });
    }
    
     public void Play(){
        playr.setFont(new Font("NewTimesRoman", Font.PLAIN, 38));
        playr.setLocation(58, 240);
        playr.setSize(207, 60);
        frame.add(playr);
        playr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
        }
             });
        }
     
         public void Ruls(){
        rull.setFont(new Font("NewTimesRoman", Font.PLAIN, 38));
        rull.setLocation(58, 350);
        rull.setSize(207, 60);
        frame.add(rull);
        rull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rules();
        }
             });
    }
    
    public void Rules(){
                JOptionPane.showMessageDialog(frame,
        "Flappy Bird имеет игровой процесс с участием 2D-графики.\n"
                + " Цель игры состоит в управлении полётом птицы,\n "
                + "которая непрерывно передвигается между рядами зелёных труб."
                + "\n При столкновении с ними происходит завершение игры.\n "
                + "Управление производится нажатием на клавишу SPACE,\n "
                + "при котором птица совершает небольшой рывок вверх.\n "
                + "При отсутствии рывков птица падает из-за силы тяжести,\n"
                + " и игра также завершается.\n "
                + "Очки набираются при каждом успешном перелёте между двумя трубами.\n"
                + "Автор: ALAKEY",
            "Правила",
            JOptionPane.QUESTION_MESSAGE);
    }

    public void Dead(){
        int xp = pipe.pipeMove();
        int WidthB = bird.getWidth(frame);
        int HeightB = bird.getHeight(frame);
        int Width1 = pipeUp.getWidth(frame);
        int playerx = player.xPosit();
        int playery = player.movePlayer();
        int wfg = fg.getWidth(frame);
        int hfg = fg.getHeight(frame);
        if ((playerx > xp - WidthB) && (playerx < xp + Width1) && (playery > pipe.posY1 - HeightB) && (playery < pipe.posY1 + Height1) ||
            (playerx > xp - WidthB) && (playerx < xp + Width1) && (playery > pipe.posY2 - HeightB) && (playery < pipe.posY2 + Height2) ||
            (playerx > 0 - WidthB) && (playerx < 0 + 356) && (playery > 470 - HeightB) && (playery < 470 + 118)){
                    endGame();
        }

    }
    
    public void restartGame() {
        if (!isRunning) {
            this.isRunning = true;
            this.player = new Player(40, 170);
            this.pipe = new Pipe(340, 0);
            playr.setEnabled(false);
            playr.setVisible(false);
            rull.setEnabled(false);
            rull.setVisible(false);
        }
    }
    
    public int endGame() {
        this.isRunning = false;
        endGameMessage();
        playr.setEnabled(true);
        playr.setVisible(true);
        rull.setEnabled(true);
        rull.setVisible(true);
        if (this.pipe.myPoints() > bestScore){
            bestScore = this.pipe.myPoints();
            writeInFile();   
        }
        return bestScore;
    }
    
    public void endGameMessage(){
        JOptionPane.showMessageDialog(frame,
        "ВЫ ПРОИГРАЛИ!\n" + "Ваш счет: " + pipe.myPoints() + "\nРекорд: " + readFile() + "\nЧтобы начать заново нажмите ОК",
            "ПРОИГРЫШ!!!",
            JOptionPane.WARNING_MESSAGE);
    }
    
    public void writeInFile(){
        String content = String.valueOf(bestScore);
            try {
            File file = new File("best.txt");
            if (!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        }
         catch (IOException e) {
            e.printStackTrace();
        }
    }
      
    public String readFile() {
        try{
          Scanner sc = new Scanner(new File("best.txt"));
          stringScore = sc.nextLine();
          sc.close();  
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return stringScore;
    }
    
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(bg, 0, 0, 357, 617, null);
        g2.setColor(Color.black);
        g2.setFont(new Font("NewTimesRoman", Font.PLAIN, 28));
        if (isRunning) {    
            g.drawImage(pipeBottom, pipe.pipeMove(), pipe.posY1, Width, Height1, null);
            g.drawImage(pipeUp, pipe.pipeMove(), pipe.posY2, Width, Height2, null);
            g.drawImage(fg, 0, 470, 356, 118, null);
            g.drawImage(bird, player.xPosit(), player.movePlayer(), null);
            g.setFont(new Font("NewTimesRoman", Font.PLAIN, 28));
            g.drawString("Счет:" + pipe.myPoints(), 20, 40);
        } 
        else {
            g2.drawImage(plays, 58, 240, null);
            g2.drawImage(ruls, 58, 350, null);
        }
        g.dispose();
    }
 
    @Override
	public void actionPerformed(ActionEvent e) {
            if (isRunning){
                Dead();
		player.movePlayer();
                pipe.randomPosition();
            }
            repaint();
	}
}