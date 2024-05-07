import java.awt.image.BufferedImage;
public class Doublehandgun extends Weapon{
    /*
     * A child of Weapon parent class 
     * Has higher damage than a handgun 
     */
    public Doublehandgun(Pair position, double damage, double rate, BufferedImage image, MainCharacter hero){
        super(position, damage, rate, image, hero);
    }
    public void pickedUp(MainCharacter hero){
        // collision with player
        super.pickedUp(hero);
        if (this.getPicked()){
            this.hero.setImage(Figures.doublehandgunwImage);
            this.setImage(Figures.doublehandgunwImage);
            this.hero.setCollider(new Pair(Figures.doublehandgunwImage.getWidth() / 2., Figures.doublehandgunwImage.getHeight() / 2.));
        }
    }
}
