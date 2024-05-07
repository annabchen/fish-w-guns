import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Menu extends JFrame {
    /*
     * A small menu with basic information that can pop up after pressing Escape
     */
    public Menu() {
        Figures fig = new Figures();
        Main main = new Main();
        this.setTitle("Menu");
        this.setSize(800, 600);
        this.setResizable(true);
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLUE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));

        JButton storyButton = new JButton(new ImageIcon(Figures.storybtnImage));
        JButton instructionsButton = new JButton(new ImageIcon(Figures.infobtwImage));
        JButton exitButton = new JButton(new ImageIcon(Figures.closebtwImage));

        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.displayInstructions();
            }
        });
        storyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.displayStory();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        panel.add(storyButton);
        panel.add(instructionsButton);
        panel.add(exitButton);

        this.add(panel);

        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }
}
