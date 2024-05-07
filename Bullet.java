import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.awt.Graphics;

public class Bullet extends EntityObj implements Updatable{
    /*
     * The bullet is a child of an entity
     * It's shot from weapons at high speed after which collision with the enemies is detected
     */
    private double damage;
    private double actualDMG;
    private double time;
    private boolean allowedDraw;
    public Bullet(double damage, BufferedImage image, Character hero){
        super(new Pair(hero.getPosition().getX(),hero.getPosition().getY()), new Pair(image.getWidth()*16, image.getHeight()*16), image, Classifier.DEFAULT);
        this.damage = damage;
        this.time = 0;
    }
    public void draw(Graphics g){
        if (allowedDraw){
            super.draw(g);
        }
    }
    public boolean collide(Enemy other){
        if (this.position.getX() < other.position.getX() + other.collider.getX() &&
            this.position.getX() + this.collider.getX() > other.position.getX() &&
            this.position.getY() < other.position.getY() + other.collider.getY() &&
            this.position.getY() + this.collider.getY() > other.position.getY() && this.allowedDraw == true) {
                this.actualDMG = damage * Math.random();
                other.decreaseHealth(actualDMG);
                this.collider = new Pair();
                this.velocity = new Pair();
                this.setAllowedDraw(false);
                if (other.getHealth() < 0){
                    other.setDamage(0);
                    other.setVelocity(new Pair());
                    other.collider = new Pair();
                    other.classifier = Classifier.DEFAULT;
                }
                return true;
        }
        return false;
    }
    public void setCollider(Pair collider){
        this.collider = collider;
    }
    public double getActualDMG(){
        return this.actualDMG;
    }
    public boolean isAllowedDraw(){
        return allowedDraw;
    }
    // not implemented yet 
    public void isDestroyed(double time){
        if (this.time > time){
            this.damage = 0;
            this.image = null;
        }
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
    public void update(double time){
        this.position.add(velocity.multiply(time));
    }
    public void setAllowedDraw(boolean allowed){
        this.allowedDraw = allowed;
    }
    public static void main(String[] args) {
        
    }
}
