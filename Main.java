import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Taskbar.Feature;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main extends JPanel implements KeyListener, MouseListener{
    /*
        This is the main game. From this the game will start 
        Defines generally all the stuff in the game from objects to hero to enemies
        The game runs in the while loop which updates and draws the world
        The world is updated through iteration in different arraylists and based on the time of the game
        The game doesn't have a goal, so just enjoy the marine world and kill those fish. Try to find all guns (4 of them)
        and kill all enemies
     */

    public static boolean paused = false;
    public static boolean launchWindow = false;
    public static MainCharacter hero;
    public static JFrame frame = new JFrame();
    public static Figures fig = new Figures();
    public static Camera cam;
    public static TileMap map;
    public static Minimap miniMap;
    public static Healthbar healthBar;
    public static EnduranceBar enduranceBar;
    public static Main window = new Main();
    public static Menu menu;
    private static Handgun handgun;
    private static Ak ak;
    private static Doublehandgun doublehandgun;
    private static Bazooka bazooka;
    private static boolean weaponOn;
    private static String file;
    private static boolean isShiftPressed;
    private static Thread soundThread;
    private static SoundPlayer soundPlayer;
    private static HashMap<String, Integer> objects = new HashMap<String, Integer>();

    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1024;
    public static final int WORLD_WIDTH = (int)(300 * 64);
    public static final int WORLD_HEIGHT = (int)(300 * 64);
    public static final int FPS = 60;
    public static int TIME; 

    public static int SPEED = 200;

    public static ArrayList<Character> characters = new ArrayList<Character>();
    public static ArrayList<Drawable> toDraw = new ArrayList<Drawable>();
    public static ArrayList<EntityObj> entities = new ArrayList<EntityObj>();
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public static ArrayList<Bullet> bigShrimpBullets = new ArrayList<Bullet>();
    public static ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    public static ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
    public static ArrayList<Location> locations = new ArrayList<Location>();



    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    //Drawing the map
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    
        resetCamera(g);
        draw(g);
    }
    public static void main(String[] args) {
        window.showIntroPage();
        while (!launchWindow) {
            frame.repaint();
        }
        if (launchWindow) {            
            window.prepareWindow();
        }
    }
    public Main(){
        this.addKeyListener(this);
        this.addMouseListener(this);
    }

    public void run()
    {
        while(true){
            if (!paused) {
                updateWorld();
            }
            repaint();
            try{
                Thread.sleep(1000 / FPS);
                TIME += 1000 / FPS;
            }
            catch(InterruptedException e){}
        }

    }
    public static void draw(Graphics g){
        if (!launchWindow){
            window.repaint();
        }
        else{
        for (Drawable drawable : toDraw) {
            drawable.draw(g);
        }
        // darkness 
        float opacity = 1f;
        Graphics2D g2d = (Graphics2D) g.create();
        if (TIME % 100000 < 20000) opacity = 0.0f;
        else if (TIME % 100000 < 30000) opacity = 0.1f;
        else if (TIME % 100000 < 40000) opacity = 0.2f;
        else if (TIME % 100000 < 50000) opacity = 0.3f;
        else if (TIME % 100000 < 60000) opacity = 0.4f;
        else if (TIME % 100000 < 70000) opacity = 0.5f;
        else if (TIME % 100000 < 80000) opacity = 0.6f;
        else if (TIME % 100000 < 90000) opacity = 0.7f;
        else if (TIME % 100000 < 100000) opacity = 0.8f;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, (int)(WORLD_WIDTH * TileMap.tileSize), (int)(WORLD_HEIGHT*TileMap.tileSize));
        if (TIME % 100000 < 60000) {
            opacity = 0.0f;
            g.drawImage(Figures.sunImage, (int)cam.getX() + (int)(Main.SCREEN_WIDTH / 3), (int)hero.getPosition().getY() - Main.SCREEN_HEIGHT / 3, 
                          (int) Figures.sunImage.getWidth(), (int)Figures.sunImage.getHeight(),null );
            }
        
        else{
            g2d.drawImage(Figures.moonImage, (int)hero.getPosition().getX(), (int)hero.getPosition().getY() - Main.SCREEN_HEIGHT / 3, 
            (int) Figures.moonImage.getWidth(), (int)Figures.moonImage.getHeight(),null );
        }
        g2d.dispose();
        
        if (bullets.size() != 0){
            for (Bullet bullet : bullets) {
                if (!bullet.isAllowedDraw() && bullet.position != hero.position){
                    g.setFont( new Font("Times New Roman", 12, 24));
                    g.drawString(""+Math.round(bullet.getActualDMG()), (int) bullet.getPosition().getX(), (int) bullet.getPosition().getY());
                }
            }
        }
        //not working 
        if (bigShrimpBullets.size() != 0){
            for (Bullet bullet : bigShrimpBullets) {
                if (!bullet.isAllowedDraw() && bullet.position != hero.position){
                    g.setFont( new Font("Times New Roman", 12, 24));
                    g.drawString(""+Math.round(bullet.getActualDMG()), (int) bullet.getPosition().getX(), (int) bullet.getPosition().getY());
                }
            }
        }
        }
    }
    public static void resetCamera(Graphics g){
        g.translate(-(int)cam.getX() , -(int)cam.getY() );
    }
    
    public void prepareWindow(){
        file = "/battlemusic.wav";
        SoundPlayer soundPlayer = new SoundPlayer(file, true);
        soundThread = new Thread(soundPlayer);
        soundThread.start();
        map = new TileMap(); 
        fig = new Figures();
        createMap();
        frame = new JFrame();
        window.startGame();
        frame.setTitle("Fish with Guns");
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(window, BorderLayout.CENTER);
        frame.add(miniMap, BorderLayout.EAST);
        frame.add(healthBar,BorderLayout.WEST);
        frame.add(enduranceBar,BorderLayout.SOUTH);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        frame.setIconImage(Figures.heroImage);
        frame.setName("FISH WITH GUNS");
        frame.setVisible(true);
        window.run();
        frame.pack();    

    }
    public void startGame(){  
        //Initiliazing
        //Characters
        hero = new MainCharacter(5000, new Pair(6500.,WORLD_HEIGHT/2.), new Pair(0,0),new Pair(0,0), new Pair(Figures.heroImage.getWidth(),Figures.heroImage.getHeight()), 
                                Figures.heroImage, Classifier.HERO);
        cam = new Camera(new Pair(hero.getPosition().getX() - SCREEN_WIDTH / 2. , hero.getPosition().getY() - SCREEN_HEIGHT / 2.), 
                                new Pair(SCREEN_WIDTH,SCREEN_HEIGHT), new Pair(WORLD_WIDTH, WORLD_HEIGHT),hero, map);
        handgun = new Handgun(new Pair(6800,WORLD_HEIGHT/2. ),1000, 30, Figures.handgunImage, hero);
        ak = new Ak(new Pair(3000 + 400,WORLD_HEIGHT/2. - 400),3000, 30, Figures.akImage, hero);
        doublehandgun = new Doublehandgun(new Pair(7800,WORLD_HEIGHT/2. + 200),2000, 30, Figures.doublehandgunImage, hero);
        bazooka = new Bazooka(new Pair(WORLD_WIDTH/2. + 2000,WORLD_HEIGHT/2. + 200),10000, 30, Figures.bazookaImage, hero);

        miniMap = new Minimap(window, cam);
        healthBar = new Healthbar(window, cam, hero);
        enduranceBar = new EnduranceBar(window, cam, hero);

        hero.setCamera(cam);
        //change something about handgun
        weapons.add(handgun);
        weapons.add(ak);
        weapons.add(doublehandgun);
        weapons.add(bazooka);
        // characters arraylist for update 
        characters.add(hero);
         // objects arraylist for collision
        entities.addAll(weapons);
        entities.addAll(characters);
        // Drawables arraylist for draw 
        toDraw.add(map);
        toDraw.addAll(locations);
        toDraw.addAll(powerUps);
        toDraw.addAll(entities);
        toDraw.addAll(bullets);
        toDraw.addAll(bigShrimpBullets);
        toDraw.add(miniMap);
        toDraw.add(healthBar);
        toDraw.add(enduranceBar);
        
       
    }
    public static void createMap(){
        //objects 
        objects.put("rock1", 10);
        objects.put("rock2", 10);
        objects.put("rock3", 10);
        objects.put("rock4", 10);
        objects.put("stalactite1",20);
        objects.put("stalactite2",20);
        objects.put("stalactite3",20);
        objects.put("stalactite4",20);
        objects.put("caveentrance",3);
        objects.put("dungeon1",3);
        //enemies
        objects.put("babyshrimp",20);
        objects.put("bigshrimp",20);
        objects.put("crab",20);
        objects.put("puffered",20);
        objects.put("salmon",20);
        objects.put("school",20);

        // powerups
        objects.put("healthpowerup", 200);

        for (Map.Entry<String, Integer> object : objects.entrySet()) {
            String k = object.getKey();
            int v = object.getValue();
            for (int i = 0; i < v; i++) {
                double x = Math.random()*WORLD_WIDTH;
                double y = Math.random()*WORLD_HEIGHT;   
                switch (k) {
                    case "rock1":
                        entities.add(new EntityObj(new Pair(x,y), new Pair(Figures.rock1Image.getWidth(), Figures.rock1Image.getHeight()), 
                                                    Figures.rock1Image, Classifier.ROCK));
                        break;
                    case "rock2":
                        entities.add(new EntityObj(new Pair(x,y), new Pair(Figures.rock2Image.getWidth(), Figures.rock2Image.getHeight()), 
                                                    Figures.rock2Image, Classifier.ROCK));
                        break;
                    case "rock3":
                        entities.add(new EntityObj(new Pair(x,y), new Pair(Figures.rock3Image.getWidth(), Figures.rock3Image.getHeight()), 
                                                    Figures.rock3Image, Classifier.ROCK));
                        break;
                    case "rock4":
                        entities.add(new EntityObj(new Pair(x,y), new Pair(Figures.rock4Image.getWidth(), Figures.rock4Image.getHeight()), 
                                                    Figures.rock4Image, Classifier.ROCK));
                        break;
                    case "caveentrance":
                        locations.add(new Location(new Pair(x,y), new Pair(Figures.caveEntranceImage.getWidth(), Figures.caveEntranceImage.getHeight()), 
                                                    Figures.caveEntranceImage, Classifier.DUNGEON));
                     
                        break;
                    case "stalactite1":
                        entities.add(new EntityObj(new Pair(x,y), new Pair(Figures.stalactite1Image.getWidth(), Figures.stalactite1Image.getHeight()), 
                                                    Figures.stalactite1Image, Classifier.ROCK));
                        break;
                    case "stalactite2":
                        entities.add(new EntityObj(new Pair(x,y), new Pair(Figures.stalactite2Image.getWidth(), Figures.stalactite2Image.getHeight()), 
                                                    Figures.stalactite2Image, Classifier.ROCK));
                        break;
                    case "stalactite3":
                        entities.add(new EntityObj(new Pair(x,y), new Pair(Figures.stalactite3Image.getWidth(), Figures.stalactite3Image.getHeight()), 
                                                    Figures.stalactite3Image, Classifier.ROCK));
                        break;
                    case "stalactite4":
                        entities.add(new EntityObj(new Pair(x,y), new Pair(Figures.stalactite4Image.getWidth(), Figures.stalactite4Image.getHeight()), 
                                                    Figures.stalactite4Image, Classifier.ROCK));
                        break;
                    case "babyshrimp":
                        enemies.add(new BabyShrimp(3000,new Pair(x,y), new Pair(Figures.babyShrimpImage.getWidth(), Figures.babyShrimpImage.getHeight()), 
                                                    Figures.babyShrimpImage, Classifier.ENEMY));
                        break;
                    case "bigshrimp":
                        enemies.add(new BigShrimp(5000,new Pair(x,y), new Pair(Figures.bigShrimpImage.getWidth(), Figures.bigShrimpImage.getHeight()), 
                                                    Figures.bigShrimpImage, Classifier.ENEMY));
                        break;
                    case "crab":
                        enemies.add(new Crab(10000,new Pair(x,y), new Pair(Figures.crabImage.getWidth(), Figures.crabImage.getHeight()), 
                                                    Figures.crabImage, Classifier.ENEMY));
                        break;
                    case "puffered":
                        enemies.add(new PufferFish(1000,new Pair(x,y),   new Pair(Figures.unpuffedImage.getWidth(), Figures.unpuffedImage.getHeight()), 
                                                    Figures.unpuffedImage, Classifier.ENEMY));
                        break;
                    case "salmon":
                        enemies.add(new Salmon(20000,new Pair(x,y), new Pair(Figures.salmonImage.getWidth(), Figures.salmonImage.getHeight()), 
                                                    Figures.salmonImage, Classifier.ENEMY));
                        break;
                    case "school":
                        enemies.add(new School(5000,new Pair(x,y), new Pair(Figures.schoolImage.getWidth(), Figures.schoolImage.getHeight()), 
                                                    Figures.schoolImage, Classifier.ENEMY));
                        break;
                    case "healthpowerup":
                        powerUps.add(new PowerUp(new Pair(x,y), Figures.healthpowerup, Classifier.POWERUP));
                        break;
                    case "dungeon1":
                        entities.add(new EntityObj(new Pair(x,y), new Pair(Figures.dungeon1Image.getWidth(), Figures.dungeon1Image.getHeight()), 
                        Figures.dungeon1Image, Classifier.DUNGEON));
                        break;
                }
            }

        }

        entities.addAll(powerUps);
        entities.addAll(locations);
        characters.addAll(enemies);
    }

    public static void updateWorld(){
        for (Enemy enemy : enemies) {
            enemy.follow(hero); // attack and follow
        }
        for (Character character : characters) {
            character.update(1 / (double)FPS);
        }
            healthBar.update(cam);
            enduranceBar.update(cam);
        for (int i = 0; i < characters.size(); i++) {
            for (int j = i+1; j < entities.size(); j++) {
                entities.get(i).collide(entities.get(j));
            }
        }
        for (PowerUp powerUp : powerUps) {
            hero.collide(powerUp);
        }
        for (Weapon weapon : weapons) {
            weapon.pickedUp(hero);
            if (weapon.getPicked()){
                bullets.addAll(weapon.getBullets());
                toDraw.addAll(bullets);
                weapon.setPicked(false);
            }
        }
        if (hero.getWeapon() != null && (!weaponOn)) {
            toDraw.addAll(bullets);
            weaponOn = true;
        }
        // shooting of bullets
        if (bullets.size() != 0) {
            for (Bullet bullet : bullets) {
                bullet.update( 1 / (double) FPS);
            }
            for (Bullet bullet : bullets) {
                for (Enemy enemy : enemies) {
                    if (bullet.collide(enemy)){ // attack enemies 
                        bullet.setAllowedDraw(false);
                    }
                    
                }
            }
        }
       
        cam.update(new Pair(hero.getPosition().getX() - SCREEN_WIDTH / 2. + SCREEN_WIDTH / 7, hero.getPosition().getY() - SCREEN_HEIGHT / 2. + SCREEN_HEIGHT / 7));
        //endurance
        if (isShiftPressed && hero.getEndurance() > 0){
            hero.setEndurance(hero.getEndurance() - 1);
        } else if (hero.getEndurance() < 99.){
            hero.setEndurance(hero.getEndurance() + .1);
        }
        if (hero.getEndurance() < 10){
            hero.setVelocity(new Pair());
        }
    }
    @Override
    public void keyPressed(KeyEvent e) { 
        int c = e.getKeyCode();
        if ((c == KeyEvent.VK_A || c == KeyEvent.VK_LEFT)&& hero.getPosition().getX() > 0 ){
            hero.setVelocity(-SPEED,true);
        }
        if ((c == KeyEvent.VK_D || c == KeyEvent.VK_RIGHT)&& hero.getPosition().getX() > 0 ){
            hero.setVelocity(SPEED,true);
        }
        if ((c == KeyEvent.VK_W || c == KeyEvent.VK_UP)&& hero.getPosition().getY() > 0){
            hero.setVelocity(-SPEED,false);
        }
        if ((c == KeyEvent.VK_S || c == KeyEvent.VK_DOWN)&& hero.getPosition().getY() < WORLD_HEIGHT){
            hero.setVelocity(SPEED,false);
        } 
        if (c == KeyEvent.VK_E){
            for (Location location : locations) {
                hero.collide(location);
            }
        }
        if (c == KeyEvent.VK_MINUS){
            cam.zoomOut();
        }
        if (c == KeyEvent.VK_EQUALS){
            cam.zoomIn();   
        }
        if (c == KeyEvent.VK_Q){
           hero.changeWeapon();
        }
        if (c == KeyEvent.VK_F6){
            for (int i = 0; i < characters.size(); i++) {
                characters.get(i).load("Save_" + i + "_.dat");
            }
        } 
        if (c == KeyEvent.VK_F5){
            for (int i = 0; i < characters.size(); i++) {
                characters.get(i).save("Save_" + i + "_.dat");
            }
        } 
        if (c == KeyEvent.VK_P){
            if (!paused) paused = true;
            else {
                paused = false;
            }
        }
        if (c == KeyEvent.VK_M){
            if (paused){
                restoreMap();
                paused = false;
            }
            else{
                showMap();
                paused = true;

            }
        }
        if (c == KeyEvent.VK_ESCAPE){
                menu = new Menu();
            
        }
        if(c == KeyEvent.VK_SHIFT && hero.getEndurance() > 0){
            isShiftPressed = true;
            hero.setVelocity(new Pair(hero.getVelocity().getX()*2, hero.getVelocity().getY()*2));
         }
         if (c == KeyEvent.VK_CONTROL){
            hero.setVelocity(new Pair(hero.getVelocity().getX()/2, hero.getVelocity().getY()/2));
         }
    }
     public void showMap(){
        miniMap.setMiniMapAllowed(false);
        healthBar.setAllowedDraw(false);
        enduranceBar.setAllowedDraw(false);
        
        for (EntityObj entityObj : entities) {
            if (entityObj.getImage() != null) entityObj.setCollider(new Pair(entityObj.getImage().getWidth()/10,entityObj.getImage().getHeight()/10));
            entityObj.setPosition(new Pair(entityObj.getPosition().getX()/WORLD_WIDTH*SCREEN_WIDTH,entityObj.getPosition().getY()/WORLD_HEIGHT*SCREEN_HEIGHT));
        }
        for (Location location : locations) {
            if (location.getImage() != null) location.setCollider(new Pair(location.getImage().getWidth()/10,location.getImage().getHeight()/10));
            location.setPosition(new Pair(location.getPosition().getX()/WORLD_WIDTH*SCREEN_WIDTH,location.getPosition().getY()/WORLD_HEIGHT*SCREEN_HEIGHT));
        }
        for (Weapon weapon : weapons) {
            if (weapon.getImage() != null) weapon.setCollider(new Pair(weapon.getImage().getWidth()/10,weapon.getImage().getHeight()/10));
            weapon.setPosition(new Pair(weapon.getPosition().getX()/WORLD_WIDTH*SCREEN_WIDTH,weapon.getPosition().getY()/WORLD_HEIGHT*SCREEN_HEIGHT));
        }
        for (int i = 0; i < 4; i++) {
            cam.zoomOut();
        }
       
     }
     public void restoreMap(){
        miniMap.setMiniMapAllowed(true);
        healthBar.setAllowedDraw(true);
        enduranceBar.setAllowedDraw(true);

        for (EntityObj entityObj : entities) {
            entityObj.setPosition(new Pair(entityObj.getPosition().getX()*WORLD_WIDTH/SCREEN_WIDTH,entityObj.getPosition().getY()*WORLD_HEIGHT/SCREEN_HEIGHT));
            if (entityObj.getImage() != null) entityObj.setCollider(new Pair(entityObj.getImage().getWidth(),entityObj.getImage().getHeight()));
        }
        for (Location location : locations) {
            location.setPosition(new Pair(location.getPosition().getX()*WORLD_WIDTH/SCREEN_WIDTH,location.getPosition().getY()*WORLD_HEIGHT/SCREEN_HEIGHT));
            if (location.getImage() != null) location.setCollider(new Pair(location.getImage().getWidth(),location.getImage().getHeight()));
        }
        for (Weapon weapon : weapons) {
            if (weapon.getImage() != null) weapon.setCollider(new Pair(weapon.getImage().getWidth() / 2,weapon.getImage().getHeight() / 2));
            weapon.setPosition(new Pair(weapon.getPosition().getX()*WORLD_WIDTH/SCREEN_WIDTH,weapon.getPosition().getY()*WORLD_HEIGHT/SCREEN_HEIGHT));
        }
        for (int i = 0; i < 4; i++) {
            cam.zoomIn();
        }
     }   
     
     public void showIntroPage(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Fish with Guns");
        frame.setSize(800, 600);
        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setBackground(Color.BLUE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4));
        JLabel background = new JLabel(new ImageIcon(Figures.introBgImage.getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
        background.setLayout(new BorderLayout());
      
        frame.setLocationRelativeTo(null);
        frame.setContentPane(background);
        frame.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
        
        JButton playButton = new JButton(new ImageIcon(Figures.playbtnImage));
        JButton storyButton = new JButton(new ImageIcon(Figures.storybtnImage));
        JButton instructionsButton = new JButton(new ImageIcon(Figures.infobtwImage));
        JButton exitButton = new JButton(new ImageIcon(Figures.closebtwImage));
        
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setEnabled(false);
                panel.removeAll();
                launchWindow = true;
                frame.dispose();
            }
        });

        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayInstructions();
            }
        });
        storyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStory();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    
        panel.add(playButton);
        panel.add(storyButton);
        panel.add(instructionsButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.pack();
        frame.repaint();
     }

    public void displayInstructions() {
        JOptionPane.showMessageDialog(this, "Controls:\nW/Up Move Up\nA/Left Move Left\nS/Down Move Down\nD/Right Move Right\nQ Swap weapons\nClick Shoot\nMouse Aim\nShift Run\nCtrl Slow\nP pause\nF5 Save State\nF6 Load Last Save State", "Information",JOptionPane.INFORMATION_MESSAGE);
    }
    public void displayStory() {
        JOptionPane.showMessageDialog(this, "Story:\nOutside of sovereign waters (>12 nautical miles from shore) for no particular reason, someone drops a handful of weapons over the side.\nNow Sal the Sardine is at the bottom of the food chain, and this particular stretch of ocean is run by gangs of larger fish who love to eat sardines.\nUnfortunately, our hero, Sal, is a sardine.\nA sardine with a mission: Survive.", "Information",JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_SHIFT){
            SPEED = Main.SPEED;
            isShiftPressed = false;
        }
        hero.setVelocity(new Pair());

    }
    @Override
    public void keyTyped(KeyEvent e) {
        int c = e.getKeyCode();
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int c = e.getButton();  
        if (c == 1){
            int posX = e.getX();
            int posY = e.getY();
            if (hero.getWeapon() != null){
                hero.shoot(posX, posY);
            }
        }
        else if (c == 2){
        }
        else if (c == 3){
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
       

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
       
    }
}

