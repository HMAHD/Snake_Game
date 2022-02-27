/**
 *
 * @author akash
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{
    
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 20;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;
    final int x[] = new int [GAME_UNITS];
    final int y[] = new int [GAME_UNITS];
    int bodyparts = 6;
    int appleEaten;
    int applex;
    int appley;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension (SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
        
    }
    public void startGame(){
    newApple();
    running = true;
    timer = new Timer(DELAY,this);
    timer.start();
    }
    //@Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        //grid for see clear -optional
        if(running){
            /*
            for(int i = 0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
            g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
            g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
        }
            */
        g.setColor(Color.red);
        g.fillOval(applex, appley, UNIT_SIZE, UNIT_SIZE);
        
        for(int i = 0; i< bodyparts;i++){
            if(i == 0){
                g.setColor(Color.green);
                g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
            }
            else{
                //g.setColor(new Color(45,180,0));
                //snake random color 
                g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
            }
        }
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Apple LiGothic",Font.BOLD,40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: "+appleEaten,(SCREEN_WIDTH - metrics.stringWidth("Score: "+appleEaten))/2, g.getFont().getSize());
        }
        else {
                gameOver(g);
                }
    }
    public void newApple(){
        applex = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appley = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    public void move(){
        for(int i = bodyparts;i>0;i--){
            x[i] = x[i-1];
            y[i] = y[i-1];           
        }
        switch(direction){
            case'U':
                y[0] =y[0] -UNIT_SIZE;
                break;
            case'D':
                y[0] =y[0] +UNIT_SIZE;
                break;
            case'L':
                x[0] =x[0] -UNIT_SIZE;
                break;
            case'R':
                x[0] =x[0] +UNIT_SIZE;
                break;
        }
    }
    public void checkApple(){
        if((x[0] ==applex)&& (y[0]==  appley)){
            bodyparts++;
            appleEaten++;
            newApple();
            
        }
    }
    public void checkCollisions(){
        //checks if head collides with body
        for(int i = bodyparts; i>0; i--){
            if((x[0] == x[i])&& (y[0] == y[i])){
                running = false;
            }
        }
        //check if head touches left border
        if(x[0] <0){
            running = false;
        }
        //check if head touches right border
        if(x[0] > SCREEN_WIDTH){
            running = false;
        }
        //if check head touches top border 
        if(y[0]< 0){
            running = false;
        }
        //if check head touches bottom border 
        if(y[0]> SCREEN_HEIGHT){
            running = false;
        }
        if(!running){
            timer.stop();
        }
    }
    public void gameOver(Graphics g){
        //score
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Apple LiGothic",Font.BOLD,40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+appleEaten,(SCREEN_WIDTH - metrics1.stringWidth("Score: "+appleEaten))/2, g.getFont().getSize());
        //Game over display
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over",(SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
        //restart
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
      if(running){
          move();
          checkApple();
          checkCollisions();
      }
      repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{
        @override
        public void KeyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
            case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
            case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
            case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
