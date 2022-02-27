/**
 *
 * @author akash
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GameFrame extends JFrame implements ActionListener{
    JButton resetButton;
    GameFrame(){
        
        //GamePanel panel = new GamePanel();
        //insted of this we can create
        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        resetButton = new JButton();
        resetButton.setText("Resatrt");
        resetButton.setSize(100,50);
        resetButton.setLocation(0,500);
        resetButton.addActionListener(this);
        
        this.add(resetButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resetButton){
          
            
        }
    }
}

