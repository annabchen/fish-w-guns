import java.awt.image.BufferedImage;

public class Bazooka extends Weapon{
    /*
     * Bazooka has the highest damage in the game.
     * Easy to see for the enemies 
     */
    public Bazooka(Pair position, double damage, double rate, BufferedImage image, MainCharacter hero){
        super(position, damage, rate, image, hero);
    }
    public Bazooka(Pair position, double damage, double rate,  Pair collider,BufferedImage image,MainCharacter hero){
        super(position, damage, rate, collider, image,hero);
    }
    public void pickedUp(MainCharacter hero){
        super.pickedUp(hero);
        if (this.picked){
            this.hero.setImage(Figures.bazookawfishImage);
            this.setImage(Figures.bazookawfishImage);
            this.hero.setCollider(new Pair(Figures.bazookawfishImage.getWidth() / 2., Figures.bazookawfishImage.getHeight() / 2.));
        }
    }
}
