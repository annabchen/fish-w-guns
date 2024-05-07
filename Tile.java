import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Tile {
    /*
     * A tile of the tileMap of the background 
     */
    private Pair position;
    private double size;
    private BufferedImage image;
    public Tile(Pair position, double size, BufferedImage image){
        this.position = position; 
        this.size = size;
        this.image = image;
    }
    public void draw(Graphics g){
        g.drawImage(image, (int) position.getX(), (int) position.getY(), (int) size, (int) size, null);
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
    public Pair getPosition(){
        return position;
    }
    public double getSize(){
        return size;
    }
    public static void main(String[] args) {
        
    }
}
