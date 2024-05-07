import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Graphics;
import java.awt.Color;

public class Character extends EntityObj implements Updatable{
    /*
     * Character
     * 
     * Defines the basic behaviour of all characters: enemies, hero
     * Also, allows loading and saving the game 
     */
    protected int health;
    protected transient Pair acceleration; 
    protected transient boolean right;

    public Character(){
        this(0,new Pair(0,0),new Pair(0,0),new Pair(0,0), new Pair(0,0), null, Classifier.DEFAULT);
    }
    public Character(int health, Pair position, Pair velocity, Pair acceleration, Pair collider, BufferedImage image, Classifier c){
        super(position, collider, image, c);
        this.health = health;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }
    public Character(int health, Pair position, Pair velocity, Pair collider, BufferedImage image) {   
        this(health, position, velocity, new Pair(0,0), collider, image, Classifier.DEFAULT);
    }
    public Character(int health, Pair position, Pair collider, BufferedImage image){
        this(health, position, new Pair(0,0), new Pair(0,0), collider, image, Classifier.DEFAULT);
    }
    public boolean getRight(){
        return this.right;
    }
    public boolean isAlive(){
        return this.health >= 0;
    }
    public void decreaseHealth(double factor){
        this.health -= factor;
    }
    public Pair getPosition(){
        return this.position;
    }
    public void setPosition(Pair position){
        this.position = position;
    }
    public Pair getVelocity(){
        return this.velocity;
    }
    public void setVelocity(Pair velocity){
        this.velocity = velocity;
    }
    public void setVelocity(double velocity, boolean horizontal){
        if (horizontal){
            this.velocity.setX(velocity);
        }
        else{
            this.velocity.setY(velocity);
        }
    }
   
    @Override
    public void draw(Graphics g){
        if (this.getHealth() > 0){
            if (this.getVelocity().getX() < 0){
                // Flip the image horizontally 
                if (this.right && !Main.paused){
                        this.flipX();
                        this.setRight(false);
                }          
            } else if (this.getVelocity().getX() > 0){
                // Flip the image horizontally 
                if (!this.right && !Main.paused){
                    this.flipX();
                    this.setRight(true);
                }
                
            }
        super.draw(g);
        }
    }
    public void setRight(boolean right){
        this.right = right;
    }
    protected void flipX(){
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-this.getImage().getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        this.setImage(op.filter(this.image, null));
    }
    public void update(double time){
        position.add(velocity.multiply(time));
    }
     public void save(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeInt(this.health);
            out.writeDouble(this.getPosition().getX());
            out.writeDouble(this.getPosition().getY());
            out.close();

        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }
    public void load(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            this.health = in.readInt();
            this.position.setX(in.readDouble());
            this.position.setY(in.readDouble());
        } catch (IOException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }
    public int getHealth(){
        return this.health;
    }
    

    public static void main(String[] args) {
        
    }
    
}
  

