public class Camera{
    /*
     * Camera is camera. 
     * Follows the player
     * Defines the visible boundaries of the world
     * You can zoom in and out the background with +/-
     */
    private Pair position;
    private Pair size;
    private Pair worldSize;
    private MainCharacter hero;
    private TileMap map;
    public Camera(Pair position, Pair size,  Pair worldSize, MainCharacter hero, TileMap tileMap){
        this.position = position;
        this.size = size;
        this.worldSize = worldSize;
        this.hero = hero;
        this.map = tileMap;
    }
    public String toString(){
        return "Position: (" + getPosition().getX() + ", " + getPosition().getY() + ")\n";
    }
    public void update(Pair pos){
        if (pos.getX() >=0  && pos.getX() <= worldSize.getX()){
            this.getPosition().setX(pos.getX());
        }
        if (pos.getY() >= 0  && pos.getY() <= worldSize.getY()){
            this.getPosition().setY(pos.getY());
        }
    }
    public double getX(){
        return getPosition().getX();
    }
    public double getY(){
        return getPosition().getY();
    }
    public Pair getPosition(){
        return position;
    }
    public void zoomOut(){
        double width = 200;
        double height = 200;
        if (TileMap.tileSize > 100){
            TileMap.tileSize /= 2;

        }
        Tile[][] pixels = this.map.getPixels();
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                pixels[row][col]= new Tile(new Pair(col*TileMap.tileSize,row*TileMap.tileSize), TileMap.tileSize, Figures.mapImage1);
            }
        }

    }
    public void zoomIn(){
        double width = 200;
        double height = 200;
        if (TileMap.tileSize < 500){
            TileMap.tileSize *= 2;
        }
        Tile[][] pixels = this.map.getPixels();
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                pixels[row][col]= new Tile(new Pair(col*TileMap.tileSize,row*TileMap.tileSize), TileMap.tileSize, Figures.mapImage1);
            }
        }
    }
    public static void main(String[] args) {
        
    }
}
