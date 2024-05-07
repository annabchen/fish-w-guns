import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
public class Salmon extends Enemy{
    /*
     * The mini-boss type of enemy in the game
     * Has a lot of health points 
     * Medium damage 
     * Has the same speed as the player 
     * Bigger observance radius 
     */
    private GameLost lost;
    private boolean attacked;
    private MainCharacter hero; 

    public Salmon(int health, Pair position, Pair velocity, Pair acceleration, Pair collider, BufferedImage image, Classifier c){
        super(health, position, velocity, acceleration, collider, image, c);
    }
    public Salmon(int health, Pair position, Pair collider, BufferedImage image, Classifier c){
        super(health, position, new Pair(), new Pair(), collider, image, c);
    }
    public Salmon(){
        super();
       
    }
    public void draw(Graphics g){
        super.draw(g); 
        if (this.attacked){
            g.setColor(Color.RED);
            g.setFont(new Font("Times New Roman", 36, 36));
            g.drawString("DANGER!!!", (int)hero.getPosition().getX(), (int)(hero.getPosition().getY() ));
            this.attacked = false;
        }
        }
    public void follow(MainCharacter hero){
        if (this.getPosition().getX() < hero.getPosition().getX() + hero.getCollider().getX()*2 &&
        this.getPosition().getX() + this.getCollider().getX()*2 > hero.getPosition().getX() &&
        this.getPosition().getY() < hero.getPosition().getY() + hero.getCollider().getY()*2 &&
        this.getPosition().getY() + this.getCollider().getY()*2 > hero.getPosition().getY() && this.image != Figures.puffedImage) {
            this.hero = hero;
            if (this.getPosition().getX() + hero.getCollider().getX()/2. < hero.getPosition().getX()){
                this.getVelocity().setX(Main.SPEED);
            }
            else if (this.getPosition().getX() > hero.getPosition().getX() + hero.getCollider().getX()/2.){
                this.getVelocity().setX(-Main.SPEED);
            }
            else {
                this.getVelocity().setX(0);
            }
            if (this.getPosition().getY() + hero.getCollider().getY()/2.< hero.getPosition().getY()){
                this.getVelocity().setY(Main.SPEED);
            }
            else if (this.getPosition().getY() > hero.getPosition().getY() + hero.getCollider().getY()/2.){
                this.getVelocity().setY(-Main.SPEED);
            }
            else {
                this.getVelocity().setY(0);
            }
            //attack simplified
            if (this.getPosition().getX() < hero.getPosition().getX() + hero.getCollider().getX() &&
            this.getPosition().getX() + this.getCollider().getX() > hero.getPosition().getX() &&
            this.getPosition().getY() < hero.getPosition().getY() + hero.getCollider().getY() &&
            this.getPosition().getY() + this.getCollider().getY() > hero.getPosition().getY()) {
                double actualDMG = damage * Math.random(); // randomized damage 
                hero.health -= actualDMG;
                this.attacked = true;
                if (!hero.isAlive() && this.getLost()== null){
                    System.out.println("You died!");
                    this.setLost(new GameLost());
                }
            }
        }
    }
    public static void main(String[] args) {
        
    }
}
