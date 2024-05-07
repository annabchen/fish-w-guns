import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;

public class Healthbar extends JPanel implements Drawable  {
    /*
     * A health indicator on the screen 
     * Has three different colors, depending on the health level 
     * Can be refilled with the health powerUps 
     * If health reaches 0, the hero dies 
     */
    private int health;
    private Pair position;
    private Pair size; 
    private JPanel bar;
    private MainCharacter hero;
    private boolean allowedDraw = true;
    public Healthbar(JPanel mainWindow, Camera cam, MainCharacter hero){
        this.bar = new JPanel();
        this.hero = hero;
        this.health = hero.getHealth();
        setLayout(null);
        this.position = new Pair(cam.getX() + Main.SCREEN_WIDTH, cam.getY());
        this.size = new Pair(hero.getHealth()/10,50);
        bar.setBounds((int)position.getX(),(int)position.getY() + 200,(int)size.getX(),(int)size.getY());
        bar.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        bar.setVisible(true);        
        mainWindow.setVisible(true);
        
    }
    public void setAllowedDraw(boolean allowed){
        this.allowedDraw = allowed;
    } 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    
        draw(g);
    }
    public void draw(Graphics g){
        //image background instead
        if (allowedDraw && hero.getHealth() >= 0){
            if (hero.getHealth() < 1000 ){
                g.drawImage(Figures.healthBar_rbg,(int)position.getX(), (int)position.getY(), (int)500, (int)size.getY(),null);
                g.drawImage(Figures.healthBar_r,(int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(),null);
            }
            else if (hero.getHealth() < 2500){
                g.drawImage(Figures.healthBar_ybg,(int)position.getX(), (int)position.getY(), (int)500, (int)size.getY(),null);
                g.drawImage(Figures.healthBar_y,(int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(),null);
            }
            else if (hero.getHealth() <= 5000){
                g.drawImage(Figures.healthBar_gbg,(int)position.getX(), (int)position.getY(), (int)500, (int)size.getY(),null);
                g.drawImage(Figures.healthBar_g,(int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(),null);
            }
            else{
                g.drawImage(Figures.healthBar_rbg,(int)position.getX(), (int)position.getY(), (int)500, (int)size.getY(),null);
                g.drawImage(Figures.healthBar_r,(int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(),null);
            }
            bar.setBounds((int)this.position.getX(),(int)this.position.getY(),(int)this.size.getX(), (int)this.size.getY());
            bar.setVisible(true);

        }
        
    }
    public void update(Camera cam){
        this.position = new Pair(cam.getX() + Main.SCREEN_WIDTH / 2, cam.getY());
        this.size = new Pair(hero.getHealth()/10,50);
    }
    public static void main(String[] args) {
        
    }
}
