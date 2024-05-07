import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Figures extends JPanel{
    /*
     * The main class for all the sprites in the game
     */
    public static BufferedImage heroImage; 
    public static BufferedImage sal_dl; 
    public static BufferedImage sal_l; 
    public static BufferedImage sal_u; 
    public static BufferedImage sal_ul; 
    public static BufferedImage mapImage1;
    public static BufferedImage mapImage2;
    public static BufferedImage entranceMarketImage; 
    public static BufferedImage healthBar_r;
    public static BufferedImage healthBar_rbg;
    public static BufferedImage healthBar_gbg;
    public static BufferedImage healthBar_g;
    public static BufferedImage healthBar_ybg;
    public static BufferedImage healthBar_y;
    public static BufferedImage staminaBar;
    public static BufferedImage staminaBar_bg;
    public static BufferedImage minimap_c;
    public static BufferedImage minimap_domain;
    public static BufferedImage minimap_e;
    public static BufferedImage minimap_o;
    public static BufferedImage minimap_sal;
    public static BufferedImage minimap_g;
    public static BufferedImage minimap_Image;
    public static BufferedImage rock1Image;  
    public static BufferedImage rock2Image;  
    public static BufferedImage rock3Image;  
    public static BufferedImage rock4Image;
    public static BufferedImage caveEntranceImage;    
    public static BufferedImage stalactite1Image;  
    public static BufferedImage stalactite2Image;  
    public static BufferedImage stalactite3Image;  
    public static BufferedImage stalactite4Image;  
    public static BufferedImage stalagmiteImage;  
    public static BufferedImage babyShrimpImage;  
    public static BufferedImage bigShrimpImage;  
    public static BufferedImage crabImage;  
    public static BufferedImage salmonImage;  
    public static BufferedImage schoolImage;  
    public static BufferedImage puffedImage;  
    public static BufferedImage unpuffedImage;  
    public static BufferedImage akImage;  
    public static BufferedImage bazookaImage;  
    public static BufferedImage doublehandgunImage;  
    public static BufferedImage handgunImage;  
    public static BufferedImage akwfishImage;  
    public static BufferedImage bazookawfishImage;  
    public static BufferedImage handgunwfishImage;  
    public static BufferedImage doublehandgunwImage;  
    public static BufferedImage smallBulletImageLeft; 
    public static BufferedImage smallBulletImageRight;  
    public static BufferedImage smallBulletImageUp;  
    public static BufferedImage smallBulletImageDown;  
    public static BufferedImage smallBulletImageUpLeft;  
    public static BufferedImage smallBulletImageUpRight;  
    public static BufferedImage smallBulletImageDownLeft;  
    public static BufferedImage smallBulletImageDownRight;  
    public static BufferedImage healthpowerup;  
    public static BufferedImage bigBulletImage;  
    public static BufferedImage sunImage;  
    public static BufferedImage moonImage;  
    public static BufferedImage crabatk1;  
    public static BufferedImage crabatk2;  
    public static BufferedImage pufferExplosion;  
    public static BufferedImage introBgImage;
    public static BufferedImage endBgImage; 
    public static BufferedImage storybtnImage;  
    public static BufferedImage infobtwImage;  
    public static BufferedImage playbtnImage;  
    public static BufferedImage closebtwImage;  
    public static BufferedImage dungeon1Image;  


    public Figures(){
        try{
            heroImage = ImageIO.read(Figures.class.getResource("/images/characters/hero.png"));
            mapImage1 = ImageIO.read(Figures.class.getResource("/images/tilemap/waterframe1.png"));
            mapImage2 = ImageIO.read(Figures.class.getResource("/images/tilemap/waterframe2.png"));
            rock1Image = ImageIO.read(Figures.class.getResource("/images/objects/rock1.png"));
            rock2Image = ImageIO.read(Figures.class.getResource("/images/objects/rock2.png"));
            rock3Image = ImageIO.read(Figures.class.getResource("/images/objects/rock3.png"));
            rock4Image = ImageIO.read(Figures.class.getResource("/images/objects/rock4.png"));
            stalactite1Image = ImageIO.read(Figures.class.getResource("/images/objects/stalactite1.png"));
            stalactite2Image = ImageIO.read(Figures.class.getResource("/images/objects/stalactite2.png"));
            stalactite3Image = ImageIO.read(Figures.class.getResource("/images/objects/stalactite3.png"));
            stalactite4Image = ImageIO.read(Figures.class.getResource("/images/objects/stalactite4.png"));
            stalagmiteImage = ImageIO.read(Figures.class.getResource("/images/objects/stalagmite.png"));
            babyShrimpImage = ImageIO.read(Figures.class.getResource("/images/characters/babyshrimp.png"));
            bigShrimpImage = ImageIO.read(Figures.class.getResource("/images/characters/bigshrimp.png"));
            crabImage = ImageIO.read(Figures.class.getResource("/images/characters/crab.png"));
            salmonImage = ImageIO.read(Figures.class.getResource("/images/characters/salmon.png"));
            schoolImage = ImageIO.read(Figures.class.getResource("/images/characters/school.png"));
            puffedImage = ImageIO.read(Figures.class.getResource("/images/characters/puffered.png"));
            unpuffedImage = ImageIO.read(Figures.class.getResource("/images/characters/unpuffed.png"));
            akImage = ImageIO.read(Figures.class.getResource("/images/weapons/ak.png"));
            bazookaImage = ImageIO.read(Figures.class.getResource("/images/weapons/bazooka.png"));
            doublehandgunImage = ImageIO.read(Figures.class.getResource("/images/weapons/doublehandgun.png"));
            handgunImage = ImageIO.read(Figures.class.getResource("/images/weapons/handgun.png"));
            akwfishImage = ImageIO.read(Figures.class.getResource("/images/characters/akwfish.png"));
            bazookawfishImage = ImageIO.read(Figures.class.getResource("/images/characters/bazookawfish.png"));
            doublehandgunwImage = ImageIO.read(Figures.class.getResource("/images/characters/doublehandgunwfish.png"));
            handgunwfishImage = ImageIO.read(Figures.class.getResource("/images/characters/handgunwfish.png"));
            caveEntranceImage = ImageIO.read(Figures.class.getResource("/images/objects/caveentrance.png"));
            smallBulletImageLeft = ImageIO.read(Figures.class.getResource("/images/objects/smallBulletLeft.png"));
            smallBulletImageRight = ImageIO.read(Figures.class.getResource("/images/objects/smallBulletRight.png"));
            smallBulletImageDown = ImageIO.read(Figures.class.getResource("/images/objects/smallBulletDown.png"));
            smallBulletImageUp = ImageIO.read(Figures.class.getResource("/images/objects/smallBulletUp.png"));
            smallBulletImageUpLeft = ImageIO.read(Figures.class.getResource("/images/objects/smallBulletUpLeft.png"));
            smallBulletImageDownLeft = ImageIO.read(Figures.class.getResource("/images/objects/smallBulletDownLeft.png"));
            smallBulletImageUpRight = ImageIO.read(Figures.class.getResource("/images/objects/smallBulletUpRight.png"));
            smallBulletImageDownRight = ImageIO.read(Figures.class.getResource("/images/objects/smallBulletDownRight.png"));
            healthpowerup = ImageIO.read(Figures.class.getResource("/images/objects/healthpowerup.png"));
            entranceMarketImage = ImageIO.read(Figures.class.getResource("/images/objects/entranceMarker.png"));
            healthBar_g = ImageIO.read(Figures.class.getResource("/images/objects/healthbar_g.png"));
            healthBar_gbg = ImageIO.read(Figures.class.getResource("/images/objects/healthbar_gbg.png"));
            healthBar_y = ImageIO.read(Figures.class.getResource("/images/objects/healthbar_y.png"));
            healthBar_ybg = ImageIO.read(Figures.class.getResource("/images/objects/healthbar_ybg.png"));
            healthBar_r = ImageIO.read(Figures.class.getResource("/images/objects/healthbar_r.png"));
            healthBar_rbg = ImageIO.read(Figures.class.getResource("/images/objects/healthbar_rbg.png"));
            staminaBar = ImageIO.read(Figures.class.getResource("/images/objects/stamina.png"));
            staminaBar_bg = ImageIO.read(Figures.class.getResource("/images/objects/stamina_bg.png"));
            minimap_c = ImageIO.read(Figures.class.getResource("/images/objects/minimap_c.png"));
            minimap_domain = ImageIO.read(Figures.class.getResource("/images/objects/minimap_domain.png"));
            minimap_e = ImageIO.read(Figures.class.getResource("/images/objects/minimap_e.png"));
            minimap_o = ImageIO.read(Figures.class.getResource("/images/objects/minimap_o.png"));
            minimap_sal = ImageIO.read(Figures.class.getResource("/images/objects/minimap_sal.png"));
            minimap_Image = ImageIO.read(Figures.class.getResource("/images/objects/minimap.png"));
            minimap_g = ImageIO.read(Figures.class.getResource("/images/objects/minimap_g.png"));
            sal_dl = ImageIO.read(Figures.class.getResource("/images/characters/sal_dl.png"));
            sal_l = ImageIO.read(Figures.class.getResource("/images/characters/sal_l.png"));
            sal_ul = ImageIO.read(Figures.class.getResource("/images/characters/sal_ul.png"));
            sal_u = ImageIO.read(Figures.class.getResource("/images/characters/sal_u.png"));
            sunImage = ImageIO.read(Figures.class.getResource("/images/objects/sun.png"));
            moonImage = ImageIO.read(Figures.class.getResource("/images/objects/moon.png"));
            crabatk1 = ImageIO.read(Figures.class.getResource("/images/characters/crabatk1.png"));
            crabatk2 = ImageIO.read(Figures.class.getResource("/images/characters/crabatk2.png"));
            pufferExplosion = ImageIO.read(Figures.class.getResource("/images/objects/puffexplosion.png"));
            introBgImage = ImageIO.read(Figures.class.getResource("/images/objects/titlescreen.png"));
            endBgImage = ImageIO.read(Figures.class.getResource("/images/objects/endscreen.png"));
            storybtnImage = ImageIO.read(Figures.class.getResource("/images/objects/storybutton.png"));
            infobtwImage = ImageIO.read(Figures.class.getResource("/images/objects/Qbutton.png"));
            playbtnImage = ImageIO.read(Figures.class.getResource("/images/objects/playbutton.png"));
            closebtwImage = ImageIO.read(Figures.class.getResource("/images/objects/escbutton.png"));
            dungeon1Image = ImageIO.read(Figures.class.getResource("/images/objects/map1final.png"));
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }
    public static void main(String[] args) {
        Figures fig = new Figures();
    }
}