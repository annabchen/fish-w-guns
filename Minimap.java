import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Minimap extends JPanel implements Drawable {
    /*
     * A rectangular minimap based on the relative surroundings of the player 
     * Different objects are presented differently on the map based on the enum Classifier
     */
    private Pair position;
    private Pair size;
    private boolean miniMapAllowed = true;
    public void draw(Graphics g){
        if (this.miniMapAllowed){
        g.setColor(Color.getHSBColor(90f / 360f, 1f, 1f));
        g.drawImage(Figures.minimap_Image,(int)getPosition().getX(), (int)getPosition().getY(), (int)getDimension().getX(), (int)getDimension().getY(),null);
        this.drawObjects(g);
    }
    }       
    public void setMiniMapAllowed(boolean allowed){
        this.miniMapAllowed = allowed;
    } 
    public Pair getPosition(){
        return this.position;
    }
    public Pair getDimension(){
        return this.size;
    }
    public void drawObjects(Graphics g){
        for (EntityObj entity : Main.entities) {
            if ((entity.getPosition().getX() > Main.hero.getPosition().getX() - Main.SCREEN_WIDTH /1.5) &&
            (entity.getPosition().getX() < Main.hero.getPosition().getX() + Main.SCREEN_WIDTH /1.5) &&
            (entity.getPosition().getY() > Main.hero.getPosition().getY() - Main.SCREEN_HEIGHT /1.5) &&
            (entity.getPosition().getY() < Main.hero.getPosition().getY() + Main.SCREEN_HEIGHT /1.5)){
                double scalePosX = entity.getPosition().getX() / Main.hero.getPosition().getX() *getDimension().getX() - this.getDimension().getX()/2;
                double scalePosY = entity.getPosition().getY() / Main.hero.getPosition().getY() * getDimension().getY() - this.getDimension().getY()/2;
                if (entity.classifier == Classifier.ROCK){
                    g.drawImage(Figures.minimap_o,(int)(scalePosX + this.getPosition().getX() ), (int)(scalePosY + this.getPosition().getY()), 25, 25 ,null);
                }
                else if (entity.classifier == Classifier.ENEMY){
                    g.drawImage(Figures.minimap_e,(int)(scalePosX + this.getPosition().getX() ), (int)(scalePosY + this.getPosition().getY()),25, 25 ,null);

                }
                else if(entity.classifier == Classifier.DUNGEON){
                    g.drawImage(Figures.minimap_domain,(int)(scalePosX + this.getPosition().getX() ), (int)(scalePosY + this.getPosition().getY()),25, 25 ,null);

                }
                else if (entity.classifier == Classifier.HERO){
                    g.drawImage(Figures.minimap_sal,(int)(scalePosX + this.getPosition().getX() ), (int)(scalePosY + this.getPosition().getY()), 25, 25 ,null);
                }
                else if(entity.classifier == Classifier.WEAPON){
                    g.drawImage(Figures.minimap_g,(int)(scalePosX + this.getPosition().getX() ), (int)(scalePosY + this.getPosition().getY()), 25, 25 ,null);
                }
                else if (entity.classifier == Classifier.DEFAULT){
                    g.drawImage(Figures.minimap_c,(int)(scalePosX + this.getPosition().getX() ), (int)(scalePosY + this.getPosition().getY()),25, 25 ,null);
                }
               
                }
            }
        }
        
    
    public Minimap(JPanel mainWindow, Camera cam){
        JPanel minimap = new JPanel();
        setLayout(null);
        this.position = cam.getPosition();
        this.size = new Pair(200,200);
        minimap.setBounds((int)getPosition().getX(),(int)getPosition().getY(),(int)getDimension().getX(),(int)getDimension().getY());
        mainWindow.add(minimap);
        
    }
    public static void main(String[] args) {
        
    }
}

