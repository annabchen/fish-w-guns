import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
public class Enemy extends Character{
    /*
     * The parent class of all enemies
     * Sets up the basic behavior for following and close-range attack 
     */
    protected double damage = 10;
    protected boolean attacked;
    protected MainCharacter hero; 
    private GameLost lost;
    public Enemy(int health, Pair position, Pair velocity, Pair acceleration, Pair collider, BufferedImage image, Classifier c){
        super(health, position, velocity, acceleration, collider, image, c);
    }
    public Enemy(int health, Pair position, Pair collider, BufferedImage image, Classifier c){
        super(health, position, new Pair(), new Pair(), collider, image, c);
    }
    public Enemy(){
        super();
       
    }
    public GameLost getLost(){
        return this.lost;
    }
    public void setLost(GameLost game){
        this.lost = game;
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
        this.getPosition().getY() + this.getCollider().getY()*2 > hero.getPosition().getY()) {
            this.hero = hero;
            if (this.getPosition().getX() + hero.getCollider().getX()/2. < hero.getPosition().getX()){
                this.getVelocity().setX(Main.SPEED/3);
            }
            else if (this.getPosition().getX() > hero.getPosition().getX() + hero.getCollider().getX()/2.){
                this.getVelocity().setX(-Main.SPEED/3);
            }
            else {
                this.getVelocity().setX(0);
            }
            if (this.getPosition().getY() + hero.getCollider().getY()/2.< hero.getPosition().getY()){
                this.getVelocity().setY(Main.SPEED/3);
            }
            else if (this.getPosition().getY() > hero.getPosition().getY() + hero.getPosition().getY()/2.){
                this.getVelocity().setY(-Main.SPEED/3);
            }
            else {
                this.getVelocity().setY(0);
            }
            //attack simplified
            this.attack();

        }
    }
    public void setCollider(Pair collider){
        this.collider = collider;
    }
    protected void attack(){
        if (this.getPosition().getX() < hero.getPosition().getX() + hero.getCollider().getX() &&
            this.getPosition().getX() + this.getCollider().getX() > hero.getPosition().getX() &&
            this.getPosition().getY() < hero.getPosition().getY() + hero.getCollider().getY() &&
            this.getPosition().getY() + this.getCollider().getY() > hero.getPosition().getY()) {
                double actualDMG = damage * Math.random(); // randomized damage 
                hero.health -= actualDMG;
                this.attacked = true;
                if (!hero.isAlive() && this.lost==null){
                    this.lost = new GameLost();
                }
            }
    }
    public void setDamage(double damage){
        this.damage = damage;
    }
    public double getDamage(){
        return this.damage;
    }
    public static void main(String[] args) {
        
    }
}
