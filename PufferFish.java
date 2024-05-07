import java.awt.image.BufferedImage;
public class PufferFish extends Enemy {
    /*
     * Puffer Fish 
     * Has high blasting damage when approached very close
     * Gets puffed when detects the player
     */
    public PufferFish(int health, Pair position, Pair velocity, Pair acceleration, Pair collider, BufferedImage image, Classifier c){
        super(health, position, velocity, acceleration, collider, image, c);
        this.damage = 1000;
    }
    public PufferFish(int health, Pair position, Pair collider, BufferedImage image, Classifier c){
        super(health, position, new Pair(), new Pair(), collider, image, c);
    }
    public PufferFish(){
        super();
       
    }
    @Override
    public void follow(MainCharacter hero){
        super.follow(hero);
        if (this.getPosition().getX() < hero.getPosition().getX() + hero.getCollider().getX()*2 &&
        this.getPosition().getX() + this.getCollider().getX()*2 > hero.getPosition().getX() &&
        this.getPosition().getY() < hero.getPosition().getY() + hero.getCollider().getY()*2 &&
        this.getPosition().getY() + this.getCollider().getY()*2 > hero.getPosition().getY() && 
        this.getImage() != Figures.puffedImage) {
            this.setImage(Figures.puffedImage);
            if (this.getVelocity().getX() > 0) {
                if (!this.getRight()) {
                    this.setRight(true);}

            } else if (this.getVelocity().getX() < 0) {
                if (this.getRight()) {
                    this.setRight(false);
                }
            }
            this.setCollider(new Pair(this.getImage().getWidth(), this.getImage().getHeight()));
            this.attack();
        }
        else if (this.getImage() == Figures.puffedImage || this.getImage() == Figures.pufferExplosion){
            if (this.getVelocity().getX() > 0) {
                if (!this.getRight()) {
                    this.flipX();
                    this.setRight(true);
                }
            } else if (this.getVelocity().getX() < 0) {
                if (this.getRight()){ 
                    this.flipX();
                    this.setRight(false);
                }
            }
        }
    }

    @Override
    public void attack(){
        if (this.getPosition().getX() < hero.getPosition().getX() + hero.getCollider().getX() &&
            this.getPosition().getX() + this.getCollider().getX() > hero.getPosition().getX() &&
            this.getPosition().getY() < hero.getPosition().getY() + hero.getCollider().getY() &&
            this.getPosition().getY() + this.getCollider().getY() > hero.getPosition().getY()) {
                hero.health -= damage;
                this.attacked = true;
                this.image = Figures.pufferExplosion;
                if (!hero.isAlive() && this.getLost()==null){ // haha
                    System.out.println("You died!");
                    this.setLost(new GameLost());
                }
            }
    }

}