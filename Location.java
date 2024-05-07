import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class Location extends EntityObj {
    /*
     * A general class for locations 
     * Possess the same behavior as entities with the ability to teleport to some place when you press E 
     * Example: cave entrance
     */
    public Location(Pair position, Pair collider, BufferedImage image, Classifier c){
        super(position, collider, image, c);
    }
    public Location(){
        super();
    }
    public void teleport(MainCharacter hero){
        hero.setPosition(new Pair(Math.random()*Main.WORLD_WIDTH, Math.random()*Main.WORLD_HEIGHT));
    }
}
