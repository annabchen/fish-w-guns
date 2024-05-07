import java.awt.image.BufferedImage;
import java.awt.Graphics;
public class Crab extends Enemy{
    /*
     * A child of class enemy 
     * Extends the basic behavior of enemy and also performs a mini-attack animation 
     */
    public Crab(int health, Pair position, Pair velocity, Pair acceleration, Pair collider, BufferedImage image, Classifier c){
        super(health, position, velocity, acceleration, collider, image, c);
    }
    public Crab(int health, Pair position, Pair collider, BufferedImage image, Classifier c){
        super(health, position, new Pair(), new Pair(), collider, image, c);
    }
    public Crab(){
        super();
       
    }
    public void draw (Graphics g){
        super.draw(g);
    }
    public void follow(MainCharacter hero){
        if (this.getVelocity().getX() > 0) this.setRight(true);
        else if (this.getVelocity().getX() < 0)this.setRight(false);
        if (Main.TIME % 4000 < 2000) {
            this.setImage(Figures.crabatk1);
        }
        else if (Main.TIME % 4000 < 4000) { 
            this.setImage(Figures.crabatk2);
        }
        super.follow(hero);

    }
}
