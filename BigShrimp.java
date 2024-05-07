import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Arrays;

public class BigShrimp extends Enemy{
    /*
     * A child of Enemy class
     * Potentially should shoot at the player. Unfortunately it doesn't. So close range attack with medium health 
     */
    private int ammo = 10;
    protected double damage = 10;
    private Bullet[] bullets = new Bullet[ammo];
    private int index = 0;
    public BigShrimp(int health, Pair position, Pair velocity, Pair acceleration, Pair collider, BufferedImage image, Classifier c){
        super(health, position, velocity, acceleration, collider, image, c);
    }
    public BigShrimp(int health, Pair position, Pair collider, BufferedImage image, Classifier c){
        super(health, position, new Pair(), new Pair(), collider, image, c);
    }
    public BigShrimp(){
        super();
       
    }
    public void initAmmo(){
        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = new Bullet(this.damage,Figures.smallBulletImageLeft, this);
        }
    }
    public List<Bullet> getBullets(){
        return Arrays.asList(bullets);
    }
    public void shoot(MainCharacter hero){
        // not implemented 
        if (bullets.length != 0 && bullets[index] != null){
            // 8 segments            
            this.bullets[index].setAllowedDraw(true);
           
            double x = this.getPosition().getX();
            double y = this.getPosition().getY();

            double fishX = hero.getPosition().getX();
            double fishY = hero.getPosition().getY();
            bullets[index].position = new Pair(fishX,fishY);
            //relative positions on screen
            //these are at angles

            if ((x>fishX-50)&&x<fishX+50 &&y>fishY){
                // should go up
                bullets[index].setVelocity(new Pair(0,1000));
                bullets[index].setImage(Figures.smallBulletImageDown);

            }

            else if (x<fishX&&y<fishY-50){
                bullets[index].setVelocity(new Pair(-1000,-1000));
                bullets[index].setImage(Figures.smallBulletImageUpLeft);
                System.out.println("x: " +x+"\ny: "+y);
            }
            else if (x>fishX&&y<fishY-50){
                bullets[index].setVelocity(new Pair(1000,-1000));
                bullets[index].setImage(Figures.smallBulletImageUpRight);
                //bullets[index].setImage(Figures.smallBulletImageUpLeft);
                System.out.println("UPright");
                System.out.println("x: " +x+"\ny: "+y);
            }
            else if (x<fishX-50&&y>fishY+50){
                bullets[index].setVelocity(new Pair(-1000,1000));
                bullets[index].setImage(Figures.smallBulletImageDownLeft);
                System.out.println("x " +x);
            }
            else if (x>fishX&&y>fishY+50){
                bullets[index].setVelocity(new Pair(1000,1000));
                //bullets[index].setImage(Figures.smallBulletImageDownLeft);
                bullets[index].setImage(Figures.smallBulletImageDownRight);
                System.out.println("Down RIGHT"+"\nx: " +x+"\ny: "+y);
            }
            else if ((y>fishY-50)&&y<fishY+50 &&x>fishX){
                bullets[index].setVelocity(new Pair(1000,01));
                bullets[index].setImage(Figures.smallBulletImageLeft);

            }
            else if ((y>fishY-50)&&y<fishY+50 &&x<fishX){
                bullets[index].setVelocity(new Pair(-1000,01));
                bullets[index].setImage(Figures.smallBulletImageRight);

            }
          
            else if ((x>fishX-50)&&x<fishX+50 &&y<1000){
                bullets[index].setVelocity(new Pair(0,500));
                bullets[index].setImage(Figures.smallBulletImageUp);

            }
           
            this.index++;
            if (index == ammo-1) {
                for (Bullet bullet : bullets) {
                    bullet.setAllowedDraw(false);
                    bullet.setCollider(new Pair(bullet.image.getWidth()*16, bullet.image.getHeight()*16));       
                         }
                this.index = 0;
                 
            }
        }
    }
}
