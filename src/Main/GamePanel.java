
package Main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileSize =16; // 16x16 tile
    final int scale = 3;
    
    public int tileSize = originalTileSize * scale; // 48x48 tile
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels 
    
    //WORLD SETTING
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    
    //FPS
    int FPS = 60;
    
    //SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler KeyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    
    //ENTITY AND OBJECT
    public Player player = new Player(this,KeyH);
    public SuperObject obj[] = new SuperObject[10];
    
    
    
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
    }
    
    public void setupGame(){
        aSetter.setObject();
        playMusic(0);
    }
    
    public void zoomInOut(int i){
        
        int oldWorldWidth = tileSize * maxWorldCol;
        tileSize += i;
        int newWorldWidth = tileSize * maxWorldCol;
        
        player.speed = (double)newWorldWidth/600;
        
        double multiplier = (double)newWorldWidth/oldWorldWidth;
        
        double newPlayerWorldX = player.worldX * multiplier;
        double newPlayerWorldY = player.worldY * multiplier;
        
        player.worldX = newPlayerWorldX;
        player.worldY = newPlayerWorldY;
    }
    
    public void startGameThread(){
        
        gameThread = new Thread(this);
        gameThread.start();
    }

//    @Override
//    public void run() {
//        
//        double drawInterval = 1000000000/FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        
//        while(gameThread != null){
//            
//            update();
//                
//            repaint();
//           
//
//            
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/100000;
//                
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//                
//                Thread.sleep((long) remainingTime);
//                
//                nextDrawTime += drawInterval;
//                
//            } catch (InterruptedException e) {
//              e.printStackTrace();
//            }
//        }
//    }
    
    public void run(){
        
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            
            lastTime = currentTime;
            
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000){
                
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        
        player.update();
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        //Tile
        tileM.draw(g2);
        
        //OBJECT
        for(int i = 0; i<obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        
        //PLAYER
        player.draw(g2);
        
        //UI
        ui.draw(g2);
        
        g2.dispose();
    }
    
    public void playMusic(int i){
        
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        
        se.setFile(i);
        se.play(); 
    }

    
}
