import java.awt.image.BufferedImage;
public class BabyShrimp extends Enemy{
    /*
     * BabyShrimp is a child of enemy class 
     * Has small detection radius and medium attack 
     */
    public BabyShrimp(int health, Pair position, Pair velocity, Pair acceleration, Pair collider, BufferedImage image, Classifier c){
        super(health, position, velocity, acceleration, collider, image, c);
    }
    public BabyShrimp(int health, Pair position, Pair collider, BufferedImage image, Classifier c){
        super(health, position, new Pair(), new Pair(), collider, image, c);
    }
    public BabyShrimp(){
        super();
       
    }
    @Override
    public void follow(MainCharacter hero){
        super.follow(hero);
    }
    public static void main(String[] args) {
        
    }
}
