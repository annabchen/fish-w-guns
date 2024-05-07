import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;

public class EnduranceBar extends JPanel implements Drawable  {
    /*
     * Endurance (Stamina) indicator of the hero
     * Refills over time 
     * Can be used to accelerate with SHIFT
     * If the stamina is low, the movement of the hero gets very slow
     * Can be hidden opening the map 
     */
    private double endurance;
    private Pair position;
    private Pair size; 
    private JPanel bar;
    private MainCharacter hero;
    private boolean allowedDraw = true;
    public EnduranceBar(JPanel mainWindow, Camera cam, MainCharacter hero){
        this.bar = new JPanel();
        this.hero = hero;
        this.endurance = hero.getEndurance();
        setLayout(null);
        this.setPosition(new Pair(cam.getX() + Main.SCREEN_WIDTH, cam.getY() + 200));
        this.setDimension(new Pair(hero.getEndurance(),50));
        bar.setBounds((int)getPosition().getX(),(int)getPosition().getY() + 200,(int)getDimension().getX(),(int)getDimension().getY());
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
    public Pair getPosition(){
        return this.position;
    }
    public void setPosition(Pair position){
        this.position = position;
    }
    public void setDimension(Pair size){
        this.size = size;
    }
    public Pair getDimension(){
        return this.size;
    }
    public void draw(Graphics g){
        //image background instead
        if (this.allowedDraw){
            g.drawImage(Figures.staminaBar_bg,(int)getPosition().getX(), (int)getPosition().getY() + 60, (int)500, (int)getDimension().getY(),null);
            g.drawImage(Figures.staminaBar,(int)getPosition().getX(), (int)getPosition().getY() + 60, (int)getDimension().getX() * 5, (int)getDimension().getY(),null);
            bar.setBounds((int)this.getPosition().getX(),(int)this.getPosition().getY(),(int)this.getDimension().getX(), (int)this.getDimension().getY());
            bar.setVisible(true);
        }
       
    }

    public void update(Camera cam){
        this.setPosition(new Pair(cam.getX() + Main.SCREEN_WIDTH / 2, cam.getY()));
        this.setDimension(new Pair(this.hero.getEndurance(),50));
    }
    public static void main(String[] args) {
        
    }
}

