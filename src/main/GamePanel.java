package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // 3x3 scale

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler handler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, handler);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(handler);
        this.setFocusable(true);
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start(); // calls the run() method
    }
    @Override
    public void run() {
        int drawInterval = 1000000000/FPS;
        double delta = 0;
        float lastTime = System.nanoTime();
        long currentTime;
        while (gameThread.isAlive()){
            currentTime = System.nanoTime();

            // How much time has passed
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            // when it reaches drawInterval
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose(); // dispose graphics context to save memory (release sys resources)
    }
}
