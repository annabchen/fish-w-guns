import java.awt.image.BufferedImage;
public class Ak extends Weapon{
    /*
     * Ak gun - a child of Weapon 
     * Has the 2nd highest damage in the game but it's easier to get detected 
     */
    public Ak(Pair position, double damage, double rate, BufferedImage image, MainCharacter hero){
        super(position, damage, rate, image, hero);
    }
    public Ak(Pair position, double damage, double rate,  Pair collider, BufferedImage image, MainCharacter hero){
        super(position, damage, rate,collider ,image,hero);
    }
    public void pickedUp(MainCharacter hero){
        super.pickedUp(hero);
        if (this.getPicked()){
            this.hero.setImage(Figures.akwfishImage);
            this.setImage(Figures.akwfishImage);
            this.hero.setCollider(new Pair(Figures.akwfishImage.getWidth() / 2,Figures.akwfishImage.getHeight() / 2));
        }
    }
    public static void main(String[] args) {
        
    }
}
