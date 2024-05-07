import java.awt.image.BufferedImage;
public class Handgun extends Weapon{
    /*
     * The first gun in the game 
     * Has small damage but also small size (harder to be detected)
     */
    public Handgun(Pair position, double damage, double rate, BufferedImage image, MainCharacter hero){
        super(position, damage, rate, image, hero);
    }
    public Handgun(Pair position, double damage, double rate,  Pair collider,BufferedImage image,MainCharacter hero){
        super(position, damage, rate, collider, image, hero);
    }
    public void pickedUp(MainCharacter hero){
        super.pickedUp(hero);
        if (this.getPicked()){
            this.hero.setImage(Figures.handgunwfishImage);
            this.setImage(Figures.handgunwfishImage);
            this.hero.setCollider(new Pair(Figures.handgunwfishImage.getWidth() / 2,Figures.handgunwfishImage.getHeight() /2));
        }
    }  
    public static void main(String[] args) {
        
    }

}
