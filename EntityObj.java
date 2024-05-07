import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class EntityObj implements Drawable {

    /*
     * The parent class of character
     * 
     * Entities are basically all the objects in the game: more specifically every class that has a position, collider, has an image, and a classifier
     */
    protected Pair position;
    protected transient Pair collider;
    protected transient Pair velocity; 
    protected transient BufferedImage image;
    protected transient Classifier classifier;

    public EntityObj(Pair position, Pair collider, BufferedImage image, Classifier c){
        this.position = position;
        this.collider = collider;
        this.velocity = new Pair();
        this.image = image;
        this.classifier = c;
    }
    public EntityObj(){
        this.position = new Pair();
        this.collider = new Pair();
        this.velocity = new Pair();
        this.image = null;
        this.classifier = Classifier.DEFAULT;
    }
    public void setCollider(Pair collider){
        this.collider = collider;
    }

    public void setPosition(Pair position){
        this.position = position;
    }
    public BufferedImage getImage(){
        return this.image;
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
    @Override
    public void draw(Graphics g){
        g.drawImage(image, (int)getPosition().getX(), (int)getPosition().getY(),(int)getCollider().getX(),(int)getCollider().getY(), null);
    }
    public void collide(EntityObj other){
        if (this.getPosition().getX() < other.getPosition().getX() + other.getCollider().getX() &&
            this.getPosition().getX() + this.getCollider().getX() > other.getPosition().getX() &&
            this.getPosition().getY() < other.getPosition().getY() + other.getCollider().getY() &&
            this.getPosition().getY() + this.getCollider().getY() > other.getPosition().getY()) {
                
            double dx = (this.getPosition().getX() + this.getCollider().getX() / 2.) - (other.getPosition().getX() + other.getCollider().getX() / 2.);
            double dy = (this.getPosition().getY() + this.getCollider().getY() / 2.) - (other.getPosition().getY() + other.getCollider().getY() / 2.);

            if (dy < 0 ){
                this.position.add(new Pair(0, this.velocity.getY()/Main.FPS ));
            } else {
                this.position.subtract(new Pair(0,this.velocity.getY()/Main.FPS ));
            }
            if (dx < 0){
                this.position.add(new Pair(this.velocity.getX()/Main.FPS,0)); 
            }else{
                this.position.subtract(new Pair(this.velocity.getX()/Main.FPS,0)); 

            }

        }
    }
    public Pair getPosition(){
        return this.position;
    }
    public Pair getCollider(){
        return this.collider;
    }
    public void setVelocity(Pair velocity){
        this.velocity = velocity;
    }
    public static void main(String[] args) {
        
    }
}
