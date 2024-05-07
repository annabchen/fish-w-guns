import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;

public class MainCharacter extends Character{
    /*
     * The main hero of our game - Sal 
     * Has simple idle animation 
     * Can possess different weapons and change them with Q 
     */
    private Camera cam;
    private boolean weaponEquipped; 
    private Weapon weapon;
    private double endurance = 100;
    private ArrayList<Weapon> weapons; 
    private int currentWeapon = -1;
    public MainCharacter(){
        super();
        this.weapons = new ArrayList<Weapon>();
    } 
    public MainCharacter(int health, Pair position, Pair velocity, Pair acceleration, Pair collider, BufferedImage image, Classifier c){
        super(health, position, velocity, acceleration, collider, image, c);
        this.weapons = new ArrayList<Weapon>();

    }
    public MainCharacter(int health, Pair position, Pair collider, BufferedImage image){
        super(health, position, collider, image);
        this.weapons = new ArrayList<Weapon>();

    }
    public void setCamera(Camera cam){
        this.cam = cam;
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
    public void addWeapon(Weapon weapon){
        weapons.add(weapon);
        this.currentWeapon += 1;
        this.weaponEquipped = true;
    }
    public void changeWeapon(){
        if (currentWeapon < weapons.size() - 1){
            this.currentWeapon++;
            this.setWeapon(weapons.get(currentWeapon));
            this.setImage(weapon.image);   
            this.collider = new Pair(weapon.image.getWidth() / 2, weapon.image.getHeight() / 2);         
            this.weaponEquipped = true;
            if (this.right && this.getVelocity().getX() < 0) {
                flipX();
                this.right = false;
            } 
            else if (!right && this.getVelocity().getX() > 0) {
                flipX();
                this.right = true;
            } 
        }
        else{
            this.currentWeapon = -1;
            this.weaponEquipped = false;
        }
    }
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
    public Weapon getWeapon(){
        if (this.weapons != null && this.currentWeapon != -1){
            return weapons.get(this.currentWeapon);
        }
        return null;
    }
    public double getEndurance(){
        return this.endurance;
    }
    public void setEndurance(double endurance){
        this.endurance = endurance;
    }
    public void draw(Graphics g){
        if (!this.weaponEquipped){
          setIdleImage();
        }
        super.draw(g);


    }
    private void setIdleImage(){
        if (Main.TIME % 5000 < 2000 && this.getVelocity().getX() <= 0) {this.setImage(Figures.sal_dl); this.setRight(false);}
        else if (Main.TIME % 5000 < 3000 && this.getVelocity().getX() <= 0){ this.setImage(Figures.sal_l); this.setRight(false);}
        else if (Main.TIME % 5000 < 4000 && this.getVelocity().getX() <= 0){ this.setImage(Figures.sal_ul); this.setRight(false);}
        else if (Main.TIME % 5000 < 5000 && this.getVelocity().getX() <= 0){ this.setImage(Figures.sal_u); this.setRight(false);}

        if (Main.TIME % 5000 < 2000 && this.getVelocity().getX() > 0) {
            this.setImage(Figures.sal_dl); flipX();
        }
        else if (Main.TIME % 5000 < 3000 && this.getVelocity().getX() > 0){
            this.setImage(Figures.sal_l); flipX();
        }
        else if (Main.TIME % 5000 < 4000 && this.getVelocity().getX() > 0) {
            this.setImage(Figures.sal_ul); flipX();
        }
        else if (Main.TIME % 5000 < 5000 && this.getVelocity().getX() > 0) {
            this.setImage(Figures.sal_u); flipX();
        }
        this.collider = new Pair(Figures.sal_dl.getWidth(),Figures.sal_dl.getHeight());
    }
    public void collide(PowerUp powerUp){
        if (this.getPosition().getX() < powerUp.getPosition().getX() + powerUp.getCollider().getX() &&
        this.getPosition().getX() + this.getCollider().getX() > powerUp.getPosition().getX() &&
        this.getPosition().getY() < powerUp.getPosition().getY() + powerUp.getCollider().getY() &&
        this.getPosition().getY() + this.getCollider().getY() > powerUp.getPosition().getY() && !powerUp.getCollected()) {   
        powerUp.setImage(null);
        powerUp.setCollected(true); 
        powerUp.setCollider(new Pair());
        this.health += 500;
        if (this.health > 5000) this.health = 5000;
    }
}
    public void collide(Location loc){
        if (this.getPosition().getX() < loc.getCollider().getX() + loc.getCollider().getX()*2 &&
        this.getPosition().getX() + this.getCollider().getX() > loc.getPosition().getX() &&
        this.getPosition().getY() < loc.getPosition().getY() + loc.getCollider().getY()*2 &&
        this.getPosition().getY() + this.getCollider().getY() > loc.getPosition().getY())  {   
            loc.teleport(this);
        }
}
    public void shoot(double x, double y){
        this.weapon.shoot(x,y);
    }
    public void setCollider(Pair collider){
        this.collider = collider;
    }
    public static void main(String[] args) {
        
    }
}