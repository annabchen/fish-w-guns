import java.util.List;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.awt.Graphics;

public abstract class Weapon extends EntityObj implements Drawable{
    /*
     * The parent class of all weapons 
     * Defines shooting and picking up of a weapon
     */

    protected double damage;
    protected double rate;
    protected BufferedImage image;
    protected Pair position;
    protected Pair collider; 
    protected boolean picked;
    protected int index = 0;
    protected MainCharacter hero;
    private int ammo = 20;
    private boolean isAllowedDraw = true;
    private Bullet[] bullets;

    public Weapon(Pair position, double damage, double rate, BufferedImage image, MainCharacter hero){
        this.damage = damage;
        this.hero = hero;
        this.rate = rate;
        this.image = image;
        this.position = position;
        this.bullets = new Bullet[ammo];
        this.initAmmo();
        this.collider = new Pair(image.getWidth(), image.getHeight());
    }
    public Weapon(Pair position, double damage, double rate, Pair collider, BufferedImage image, MainCharacter hero){
        this(position, damage, rate, image, hero);
        this.collider = collider;
    }
    public Pair getPosition(){
        return this.position;
    }
    public boolean getPicked(){
        return this.picked;
    }
    public void setPicked(boolean picked){
        this.picked = picked;
    }
    public double getDamage(){
        return this.damage;
    }
    public void draw(Graphics g){
        this.setCollider(new Pair(this.getImage().getWidth() / 2,this.getImage().getHeight() / 2));
        if (this.isAllowedDraw()) g.drawImage(this.getImage(), (int)getPosition().getX(), (int)getPosition().getY(), (int)getCollider().getX(),(int)getCollider().getY(), null);
    }
    public boolean isAllowedDraw(){
        return this.isAllowedDraw;
    }
    public void setAllowedDraw(){
        this.isAllowedDraw = true;
    }
    public void shoot(double x, double y){
        if (this.bullets[index] != null){
            // 8 segments
            this.bullets[index].setAllowedDraw(true);
            double scaleX = (double) hero.getPosition().getX() / Main.WORLD_WIDTH  ;
            double scaleY = (double)  hero.getPosition().getY() / Main.WORLD_HEIGHT;
            double X = (Main.SCREEN_WIDTH * scaleX);
            double Y = (Main.SCREEN_HEIGHT * scaleY);
            double dx = X - x; // screen x 
            double dy = Y - y;

            double posX = hero.getPosition().getX();
            double posY = hero.getPosition().getY();
            bullets[index].position = new Pair(posX,posY);
            //relative positions on screen
            double fishX=Main.SCREEN_WIDTH/2 - Main.SCREEN_WIDTH / 7;
            double fishY=Main.SCREEN_HEIGHT/2 - Main.SCREEN_HEIGHT / 7;
            //these are at angles

            if ((x>fishX-50)&&x<fishX+50 &&y>fishY){
                // should go up
                bullets[index].setVelocity(new Pair(0,1000));
                bullets[index].setImage(Figures.smallBulletImageDown);

            }
            else if ((x>fishX-50)&&x<fishX+50){
                bullets[index].setVelocity(new Pair(01,-1000));
                bullets[index].setImage(Figures.smallBulletImageUp);
            }
            else if (x<fishX&&y<fishY-50){
                bullets[index].setVelocity(new Pair(-1000,-1000));
                bullets[index].setImage(Figures.smallBulletImageUpLeft);
            }
            else if (x>fishX&&y<fishY-50){
                bullets[index].setVelocity(new Pair(1000,-1000));
                bullets[index].setImage(Figures.smallBulletImageUpRight);
            }
            else if (x<fishX-50&&y>fishY+50){
                bullets[index].setVelocity(new Pair(-1000,1000));
                bullets[index].setImage(Figures.smallBulletImageDownLeft);
            }
            else if (x>fishX&&y>fishY+50){
                bullets[index].setVelocity(new Pair(1000,1000));
                bullets[index].setImage(Figures.smallBulletImageDownRight);
            }
            else if ((y>fishY-50)&&y<fishY+50 &&x>fishX){
                bullets[index].setVelocity(new Pair(1000,01));
                bullets[index].setImage(Figures.smallBulletImageLeft);

            }
            else if ((y>fishY-50)&&y<fishY+50 &&x<fishX){
                bullets[index].setVelocity(new Pair(-1000,01));
                bullets[index].setImage(Figures.smallBulletImageRight);

            }
            this.index++;
            if (index >= ammo-1) {
                for (Bullet bullet : bullets) {
                    bullet.setAllowedDraw(false);
                    bullet.setCollider(new Pair(bullet.image.getWidth()*16, bullet.image.getHeight()*16));       
                }
                this.index = 0;
                 
            }
        }
    }
    public void initAmmo(){
        for (int i = 0; i < bullets.length; i++) {
            this.bullets[i] = new Bullet(this.damage,Figures.smallBulletImageLeft, (Character)this.hero);
        }
    }
    public List<Bullet> getBullets(){
        return Arrays.asList(bullets);
    }
    public void pickedUp(MainCharacter hero){
            if (this.getPosition().getX() < hero.getPosition().getX() + hero.getCollider().getX() &&
            this.getPosition().getX() + this.getCollider().getX() > hero.getPosition().getX() &&
            this.getPosition().getY() < hero.getPosition().getY() + hero.getCollider().getY() &&
            this.getPosition().getY() + this.getCollider().getY() > hero.getPosition().getY() && this.isAllowedDraw()) {
                this.isAllowedDraw = false;
                this.hero.addWeapon(this);
                this.hero.setWeapon(this);
                this.initAmmo();
                this.position = new Pair(hero.getPosition().getX(), hero.getPosition().getY());
                if (this.hero.getVelocity().getX() > 0) {
                    if (this.hero.right) hero.setRight(true);
                    else{
                        this.hero.setRight(false);
                        this.hero.flipX();
                    }
                }
                if (this.hero.getVelocity().getX() < 0) {
                    if (!this.hero.right) hero.setRight(false);
                    else{
                        this.hero.setRight(true);
                        this.hero.flipX();
                    }
                }
                this.setPicked(true);
            }
        
    }  
    public void setImage(BufferedImage image){
        this.image = image;
    }
    public BufferedImage getImage(){
        return this.image;
    }
     public static void main(String[] args) {
    }
}