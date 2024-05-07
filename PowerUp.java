import java.awt.image.BufferedImage;

public class PowerUp extends EntityObj{
    /*
     * A PowerUp class is a child of Entity
     * Can be collected and right now serves as a health boost
     */
    private boolean collected;
    public PowerUp (Pair position, BufferedImage image,Classifier c){
        super(position, new Pair(25,25), image , c);
    }
    @Override
    public void collide(EntityObj other) {
        if (this.getPosition().getX() < other.getPosition().getX() + other.getCollider().getX() &&
        this.getPosition().getX() + this.getCollider().getX() > other.getPosition().getX() &&
        this.getPosition().getY() < other.getPosition().getY() + other.getCollider().getY() &&
        this.getPosition().getY() + this.getCollider().getY() > other.getPosition().getY()) {   
            this.setImage(null);
         }
        }
    public boolean getCollected(){
        return this.collected;
    }
    public void setCollected(boolean collected){
        this.collected = collected;
    }

public static void main(String[] args) {
    
}
}  
