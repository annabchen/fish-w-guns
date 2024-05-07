import java.awt.image.BufferedImage;
import java.awt.Graphics;
public class School extends Enemy{
    /*
     * The school of fish is a child of enemy 
     * Has three fish in a school 
     * Average damage, average detection range 
     */
    public School(int health, Pair position, Pair velocity, Pair acceleration, Pair collider, BufferedImage image, Classifier c){
        super(health, position, velocity, acceleration, collider, image, c);
    }
    public School(int health, Pair position, Pair collider, BufferedImage image, Classifier c){
        super(health, position, new Pair(), new Pair(), collider, image, c);
    }
    public School(){
        super();
       
    }
    
}
