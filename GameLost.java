import javax.swing.*;
import java.awt.*;
public class GameLost extends JFrame {
    /*
     * The end screen after the hero dies 
     * Pops up on top of the existing screen
     * Can only be closed 
     */
    public GameLost() {
        this.setTitle("Game Over");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Figures fig = new Figures();
        JLabel background = new JLabel(new ImageIcon(Figures.endBgImage.getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
        background.setLayout(new BorderLayout());


        JButton playButton = new JButton(new ImageIcon(Figures.playbtnImage));
        JButton storyButton = new JButton(new ImageIcon(Figures.storybtnImage));
        JButton instructionsButton = new JButton(new ImageIcon(Figures.infobtwImage));
        
        this.setLocationRelativeTo(null);
        this.setContentPane(background);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new GameLost();
    }
}
