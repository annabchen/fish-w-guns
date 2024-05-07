import java.awt.image.BufferedImage;
public class Shrimp extends Enemy {
    /*
     * A child of enemy class 
     * Average damage, average detection limit
     */
    public Shrimp(int health, Pair position, Pair velocity, Pair acceleration, Pair collider, BufferedImage image, Classifier c){
        super(health, position, velocity, acceleration, collider, image, c);
    }
    public Shrimp(int health, Pair position, Pair collider, BufferedImage image, Classifier c){
        super(health, position, new Pair(), new Pair(), collider, image, c);
    }
    public Shrimp(){
        super();
       
    }
}
